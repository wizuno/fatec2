package br.sceweb.modelo;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.sceweb.servico.FabricaDeConexoes;

public class ConvenioDAO {

	Logger logger = Logger.getLogger(ConvenioDAO.class);
	
	public int adiciona(Convenio convenio){
		PreparedStatement ps = null;
		int codigoRetorno=0;
		Connection conn = null;
		try {
			conn = new FabricaDeConexoes().getConnection();
			ps = (PreparedStatement) conn.prepareStatement(
					"insert into convenio (empresa_cnpj, dataInicio, dataFim) values(?,?,?)");
			ps.setString(1,convenio.getCNPJ());
			ps.setString(2, convenio.getDataInicio().toString("YYYY-MM-dd"));
			ps.setString(3, convenio.getDataTermino().toString("YYYY-MM-dd"));
			codigoRetorno = ps.executeUpdate();
			logger.info("codigo de retorno do metodo adiciona convenio = " + codigoRetorno);
			ps.close();
		} catch (SQLException e){
			logger.info("convenioDAO metodo adiciona -" + e.getStackTrace());
		
		try{
			conn.rollback();
		} catch (SQLException ex){
			logger.info("convenioDAO SQLException -" + ex.getMessage());
		  }
		} finally {
			if(ps != null){
				try{
					ps.close();
				} catch (SQLException e1){
					logger.info("convenioDAO metodo adiciona -" + e1.getStackTrace());
				}
			}
		}
	return codigoRetorno;
	}
	
	public int exclui (String snpj) {
		java.sql.PreparedStatement ps;
		int codigoretorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps= conn.prepareStatement ("delete from convenio where empresa_cnpj = ?");
			ps.setString(1, snpj);
			codigoretorno = ps.executeUpdate();
			}
		catch (SQLException e){
			throw new RuntimeException(e);
		}
	return codigoretorno;
	
	}
}
