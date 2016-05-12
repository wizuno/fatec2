package br.sceweb.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;



public class Convenio {
	private String cnpj;
	private DateTime dataInicio;
	private DateTime dataTermino;
	Logger logger = Logger.getLogger(Convenio.class);

	public Convenio(String cnpj, String dataInicio, String dataTermino) {
		setCNPJ(cnpj);
		setDataInicio(dataInicio);
		setDataTermino(dataTermino);
	}

	public String getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(String cnpj) {
		if (cnpj.length()==14){
			this.cnpj = cnpj;
			}
			else
			throw new IllegalArgumentException("CNPJ inv�lido!");
	}

	public DateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		logger.info("data inicio = " + dataInicio);
		if (validaData(dataInicio)) {
			this.dataInicio = new DateTime(Integer.parseInt(dataInicio.substring(6, 10)),
					Integer.parseInt(dataInicio.substring(3, 5)), Integer.parseInt(dataInicio.substring(0, 2)), 0, 0);
		} else {
			throw new IllegalArgumentException("data invalida");
		}
	}

	public DateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		logger.info("data termino = " + dataTermino);
		if (validaData(dataTermino)) {
			this.dataTermino = new DateTime(Integer.parseInt(dataTermino.substring(6, 10)),
					Integer.parseInt(dataTermino.substring(3, 5)), Integer.parseInt(dataTermino.substring(0, 2)), 0, 0);
		} else {
			throw new IllegalArgumentException("data invalida");
		}
	}

	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		df.setLenient (false); //
		try {
		df.parse (data); // data v�lida
		return true;
		} catch (ParseException ex) {
		logger.error("Erro na validacao de data - " + ex.getMessage());
		return false;
		}
	}
	
}
