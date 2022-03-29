package it.polito.tdp.corsi.db;

import java.sql.*;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List<Corso> getCorsiByPeriod(Integer period) {
		String sql = "select * "
				+ "from corso "
				+ "where pd = ?";
		
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, period);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			rs.close();
			st.close();
			conn.close();
			return result;
		} catch (SQLException e){
			System.out.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
	
	}
	
	public Map<Corso, Integer> getIscritti(int periodo) {
		String sql = "select c.codins, c.crediti, c.nome, c.pd, count(*) as n "
				+ "from corso c, iscrizione i "
				+ "where c.codins = i.codins and pd = ? "
				+ "group by c.codins, c.crediti, c.nome, c.pd";
		
		Map<Corso, Integer> result = new HashMap<Corso, Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				result.put(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")), rs.getInt("n"));
			}
			rs.close();
			st.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
	}
}
