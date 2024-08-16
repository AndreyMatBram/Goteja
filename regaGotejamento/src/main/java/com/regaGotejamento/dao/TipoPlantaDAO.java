package com.regaGotejamento.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.regaGotejamento.models.TipoPlanta;

public class TipoPlantaDAO {

	private EntityManager entityManager;
	
	public TipoPlantaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void salvarTipoPlanta(TipoPlanta tipoPlanta) {
		getEntityManager().persist(tipoPlanta);
	}
	
	public TipoPlanta alterarTipoPlanta(TipoPlanta tipoPlanta) {
		return getEntityManager().merge(tipoPlanta);
	}
	
	public TipoPlanta consultaTipoPlantaId(Long id) {
		return getEntityManager().find(TipoPlanta.class, id);
	}
	
	public void removeTipoPlanta(Long id) {
		TipoPlanta tipoPlanta = consultaTipoPlantaId(id);
		getEntityManager().remove(tipoPlanta);
	}
	
	public List<TipoPlanta> listaTipoPlanta() {
		List<TipoPlanta> tipoPlantas = new ArrayList<>();
		TypedQuery<TipoPlanta> query = getEntityManager().createQuery("SELECT tipoPlanta FROM TAB_TIPO_PLANTA tipoPlanta", TipoPlanta.class);
		tipoPlantas = query.getResultList();
		return tipoPlantas;
	}
	
	public List<TipoPlanta> listaTipoPlantaNome(String tipo){
		List<TipoPlanta> tipoPlantas = new ArrayList<>();
		TypedQuery<TipoPlanta> query = getEntityManager().createQuery("SELECT tp FROM TAB_TIPO_PLANTA ttp where c.tipo =:tipo", TipoPlanta.class);
		query.setParameter("tipo", tipo);
		tipoPlantas = query.getResultList();
		return tipoPlantas;
	}
}