package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.Connection;

import br.sceweb.modelo.Convenio;
import br.sceweb.modelo.ConvenioDAO;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;
import br.sceweb.servico.FabricaDeConexoes;

public class UC05FBCadastrarConvenio {
	static Convenio convenio;
	static ConvenioDAO convenioDAO;
	static Convenio novoConvenio;
	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	static String cnpj;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("81965361000174");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
		empresaDAO.adiciona(empresa);
		convenioDAO = new ConvenioDAO();
		convenio = new Convenio("81965361000174","03/05/2016","20/05/2016");
	}
	
	@Test
	public void CT05UC05A2Cadastrar_com_sucesso() {
		assertEquals(1,convenioDAO.adiciona(convenio));
	}
	
	@Test
	public void CT06UC05A3Cadastrar_convenio_cpnj_nao_cadastrado(){
		novoConvenio = new Convenio("81965361000155","03/05/2016","20/05/2016");
		assertEquals(0,convenioDAO.adiciona(novoConvenio));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CT07UC05A4CadastrarConvenio_cnpj_invalido(){
		cnpj = "1111";
		convenio.setCNPJ(cnpj);
		
	}
	
	@Test
	public void CT08UC05A5Cadastrar_convenio_data_invalida(){
		assertFalse(convenio.validaData("42/05/2016"));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		convenioDAO.exclui("81965361000174");
		empresaDAO.exclui("81965361000174");
		
	}



}
