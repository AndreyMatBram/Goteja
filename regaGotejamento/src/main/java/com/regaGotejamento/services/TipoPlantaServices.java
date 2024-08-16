package com.regaGotejamento.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import com.regaGotejamento.dao.TipoPlantaDAO;
import com.regaGotejamento.models.TipoPlanta;
import com.regaGotejamento.persistencia.ConexaoBancoDados;

public class TipoPlantaServices {

	@PersistenceContext(unitName = "projetoMicroEventos")
	private final EntityManager entityManager;

	private TipoPlantaDAO dao;
	private EntityTransaction tx;

	public TipoPlantaServices() {
		entityManager = ConexaoBancoDados.getConexao().getEntityManager();
		dao = new TipoPlantaDAO(entityManager);
	}

	public TipoPlantaDAO getDao() {
		return dao;
	}

	public EntityTransaction getTx() {
		return tx;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void salvarTipoPlanta(TipoPlanta tipoPlanta) {
		tx = getEntityManager().getTransaction();

		try {
			getTx().begin();
			getDao().salvarTipoPlanta(tipoPlanta);
			getTx().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (getTx().isActive()) {
				getTx().rollback();
			}
		} finally {
			getEntityManager().close();
		}
	}

	public TipoPlanta alterarTipoPlanta(TipoPlanta tipoPlanta) {
		
		tx = getEntityManager().getTransaction();
		try {
			getTx().begin();
			TipoPlanta tipoAtual = getDao().alterarTipoPlanta(tipoPlanta);
			getTx().commit();
			return tipoAtual;
		} catch (Exception e) {
			e.printStackTrace();
			if (getTx().isActive()) {
				getTx().rollback();
			}
		} finally {
			getEntityManager().close();
		}
		return null;
	}
	
	public void excluirTipoPlanta(Long id) {
		tx = getEntityManager().getTransaction();
		try {
			getTx().begin();
			getDao().removeTipoPlanta(id);
			getTx().commit();
		}catch(Exception e) {
			e.printStackTrace();
			if (getTx().isActive()) {
				getTx().rollback();
			}
		}finally {
			getEntityManager().close();
		}
	}
	
	public TipoPlanta consultaTipoPlanta(long id) {
		TipoPlanta tipoPlanta = new TipoPlanta();
		tipoPlanta = dao.consultaTipoPlantaId(id);
		return tipoPlanta;
	}
	
	public List<TipoPlanta> listaTipoPlanta(){
		return dao.listaTipoPlanta();
	}
	
	public List<TipoPlanta> listaTipoPlantaNome(){
		return null;
	}
}
