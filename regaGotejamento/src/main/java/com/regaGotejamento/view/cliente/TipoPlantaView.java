package com.regaGotejamento.view.cliente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.regaGotejamento.models.TipoPlanta;
import com.regaGotejamento.services.TipoPlantaServices;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TipoPlantaView extends JFrame {

	private static final long serialVersionUID = 601585769967144560L;
	private JPanel contentPane;
	private JTextField txtTipoPlanta;
	private JLabel lblTipo;
	private JPanel panelMenu;
	private JLabel lblTituloTipoPlanta;
	private JButton btnAdicionar;
	private JButton btnCancelar;
	private JButton btnRemover;
	private long idTipoPlanta;
	private TipoPlantaServices tipoPlantaServices;
	private TipoPlanta tipoPlanta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TipoPlantaView frame = new TipoPlantaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TipoPlantaView() {
		initComponents();
		eventHandler();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTipo = new JLabel("Tipo de Planta:");
		lblTipo.setBounds(20, 80, 110, 15);
		lblTipo.setFont(new Font("Verdana", Font.PLAIN, 13));
		contentPane.add(lblTipo);

		panelMenu = new JPanel();
		panelMenu.setBackground(Color.GRAY);
		panelMenu.setBounds(0, 0, 484, 33);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		lblTituloTipoPlanta = new JLabel("Cadastrar Tipos de Planta");
		lblTituloTipoPlanta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTipoPlanta.setFont(new Font("Verdana", Font.BOLD, 13));
		lblTituloTipoPlanta.setBounds(150, 40, 190, 15);
		contentPane.add(lblTituloTipoPlanta);

		txtTipoPlanta = new JTextField();
		txtTipoPlanta.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtTipoPlanta.setBounds(135, 80, 250, 20);
		contentPane.add(txtTipoPlanta);
		txtTipoPlanta.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Verdana", Font.BOLD, 13));
		btnAdicionar.setBounds(20, 250, 120, 20);
		contentPane.add(btnAdicionar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.BOLD, 13));
		btnCancelar.setBounds(150, 251, 120, 20);
		contentPane.add(btnCancelar);

		btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Verdana", Font.BOLD, 13));
		btnRemover.setBounds(289, 251, 120, 20);
		contentPane.add(btnRemover);
	}

	private void eventHandler() {

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idTipoPlanta == 0L) {
					salvarTipoPlanta();
				} else {
					alterarTipoPlanta();
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirTipoPlanta();
			}
		});
	}

	/*
	 * Rotinas para Inclusão, Alteração e Exclusão de um registro Manutenção de
	 * cadastro
	 */
	public void salvarTipoPlanta() {
		tipoPlantaServices = getTipoPlantaService();
		tipoPlanta = getTipoPlanta();

		setTipoPlantaFromView();

		tipoPlantaServices.salvarTipoPlanta(tipoPlanta);

		limparTipoPlantaView();
	}

	public void alterarTipoPlanta() {
		tipoPlantaServices = getTipoPlantaService();
		tipoPlanta = getTipoPlanta();

		tipoPlanta.setId(idTipoPlanta);
		setTipoPlantaFromView();

		tipoPlantaServices.alterarTipoPlanta(tipoPlanta);

		limparTipoPlantaView();
	}

	public void excluirTipoPlanta() {
		tipoPlantaServices = getTipoPlantaService();

		tipoPlantaServices.excluirTipoPlanta(idTipoPlanta);

		limparTipoPlantaView();
	}

	public void consultarTipoPlanta() {
		tipoPlantaServices = getTipoPlantaService();
		tipoPlanta = getTipoPlanta();
		Long id = 1L;
		tipoPlanta = tipoPlantaServices.consultaTipoPlanta(id);
		getTipoPlantaFromDataBase();
	}

	public void listarTipoPlanta() {
		tipoPlantaServices = getTipoPlantaService();
		List<TipoPlanta> tiposPlantas = new ArrayList<>();

		tiposPlantas = tipoPlantaServices.listaTipoPlanta();

		for (TipoPlanta tipoPlanta : tiposPlantas) {
			System.out.println(tipoPlanta.toString());
		}
	}

	/*
	 * Rotinas de apoio
	 */
	private void limparTipoPlantaView() {
		idTipoPlanta = 0L;
		txtTipoPlanta.setText("");
	}

	private void setTipoPlantaFromView() {
		idTipoPlanta = 0L;
		tipoPlanta.setTipo(txtTipoPlanta.getText());
	}

	private void getTipoPlantaFromDataBase() {
		idTipoPlanta = tipoPlanta.getId();

		txtTipoPlanta.setText(tipoPlanta.getTipo());
	}

	private TipoPlanta getTipoPlanta() {
		return new TipoPlanta();
	}

	private TipoPlantaServices getTipoPlantaService() {
		return new TipoPlantaServices();
	}
}
