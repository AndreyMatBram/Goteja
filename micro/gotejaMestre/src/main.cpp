#include <Arduino.h>
#include <LiquidCrystal_I2C.h>
#include <string.h>

#define BT1 4
#define BT2 13
#define SWT 5

using namespace std;

/*
TODO LIST:
Atribuicao automatica de ID distribuidos pelo MESTRE
*/

// Variaveis
char *msg;
float PUmidade;
float media;
int stateSWT;
LiquidCrystal_I2C lcd(0x27, 16, 4);
int j; // J indica o numero do campo

// TODO: Funcao de comunicacao serial
void slaveCOM(int const slave, String msg, float const PUmidade)
{

  // Procura o escravo para solicitar inforcao
  Serial.write(slave);
  Serial.print(PUmidade);
  // Quando a informacao e recebida
  if (Serial.available())
  {
    // Atualiza a mensagem
    msg = Serial.readString();
  }
}

void setup()
{

  // Iniciar a Serial
  Serial.begin(9600);

  // Iniciar variaveis
  j = 1;
  PUmidade = 50;
  media = 50;
  msg = nullptr;

  // Definir os pinos
  pinMode(BT1, INPUT_PULLDOWN);
  pinMode(BT2, INPUT_PULLDOWN);
  pinMode(SWT, INPUT);

  // Iniciar o LCD
  lcd.begin(0x27, 16, 4);
  lcd.backlight();
}

void loop()
{
  msg = "";
  delay(250);
  // Efetuar comunicacao com o escravo responsavel pelo campo selecionado
  slaveCOM(j, msg, PUmidade);

  // Verificar o estado da chave de selecao
  stateSWT = digitalRead(SWT);
  if (stateSWT == 1)
  {
    // Selecao de campo
    if (digitalRead(BT1) == 1)
    {
      j++;
    }
    if (digitalRead(BT2) == 1)
    {
      j--;
    }
   Serial.println("Campo: " +String(j));
  }
  else
  {
    // Controle do parametro umidade
    if (digitalRead(BT1) == 1)
    {
      PUmidade++;
    }
    if (digitalRead(BT2) == 1)
    {
      PUmidade--;
    }
    Serial.println("Parametro: " + String(PUmidade));
  }

  // Limpar o LCD e exibir a mensagem
  lcd.clear();
  lcd.cursor();
  lcd.backlight();
  lcd.print(msg);
  Serial.println(msg);
  delay(500);
}