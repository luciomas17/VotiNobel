package it.polito.tdp.model;

import java.util.List;

import it.polito.tdp.dao.EsameDAO;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		Model model = new Model();
		model.calcolaSottoinsiemeEsami(20);
		*/
		
		EsameDAO dao = new EsameDAO();
		List<Esame> esami = dao.getTuttiEsami();
		
		int voto;
		
		for(Esame e : esami) {
			voto = e.getVoto();
			System.out.println(voto + "\n");
		}
		
	}

}
