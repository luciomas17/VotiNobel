package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.dao.EsameDAO;

public class Model {
	
	private List<Esame> esami;
	private List<Esame> best;
	private double media_best;

	public Model() {
		EsameDAO dao = new EsameDAO() ;
		this.esami = dao.getTuttiEsami() ;
	}
	
	/**
	 * Trova la combinazione di corsi avente la somma dei crediti richiesta
	 * che abbia la media massima
	 * @param numeroCrediti
	 * @return l'elenco dei corsi ottimale, oppure {@code null} se non esiste
	 * alcuna combinazione di corsi che assomma al numero esatto di crediti.
	 */
	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		
		best = null;
		media_best = 0;
		
		Set<Esame> parziale = new HashSet<Esame>();
		
		cerca(parziale, 0, numeroCrediti);
		
		return best;
	}
	
	private void cerca(Set<Esame> parziale, int L, int m) {
		
		int crediti = sommaCrediti(parziale);
		
		if(crediti > m)
			return;
		
		if(crediti == m) {
			double media = calcolaMedia(parziale);
			
			if(media > media_best) {
				best = new ArrayList<Esame>(parziale);
				media_best = media;
				return;
				
			} else 
				return;
		}
		
		if(L == esami.size())
			return;
		
		cerca(parziale, L+1, m);
		
		if(esami.get(L).getVoto() != 0) {
			parziale.add(esami.get(L));
			cerca(parziale, L+1, m);
			parziale.remove(esami.get(L));
		}
	}

	private double calcolaMedia(Set<Esame> esami) {
		double somma = 0;
		int crediti = 0;
		
		for(Esame e : esami) {
			somma += e.getVoto() * e.getCrediti();
			crediti += e.getCrediti();
		}
		
		return somma / crediti;
	}

	private int sommaCrediti(Set<Esame> esami) {
		int somma = 0;
		
		for(Esame e : esami)
			somma += e.getCrediti();
		
		return somma;
	}

}
