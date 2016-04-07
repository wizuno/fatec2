package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {

	static Empresa empresa = new Empresa();
	static EmpresaDAO empresaDAO = new EmpresaDAO();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresa.setNomeDaEmpresa("empresax");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresax");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("222");
	
	}


	@Test
	public void CT01UC02FBConsultarEmpresa_com_sucesso() {
		
		empresaDAO.adiciona(empresa);
		assertTrue(empresa.equals(empresaDAO.consultaEmpresa("89424232000180")));
		empresaDAO.exclui("89424232000180");
	}
	
	public void CT02UC02FBConsultarEmpresa_cnpj_invalido() {
		
				assertNull(empresaDAO.consultaEmpresa("1111"));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		empresaDAO.exclui("89424232000180");
	}

}
