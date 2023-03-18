package br.com.work.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.work.domain.enuns.Genero;
import br.com.work.domain.enuns.Serie;
import br.com.work.domain.enuns.Turma;
import br.com.work.domain.enuns.Turno;

@Entity
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 15, max = 60, message = "Insira o nome Completo com mais de 15 caracteres")
	private String nome_completo;
	
	@NotNull(message = "Idade inexistente")
	@Max(value = 110, message = "Numeração de Idade não aceita.")
	@Min(value = 1, message = "Numeração de Idade não aceita.")
	private Integer idade;
	
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Pattern(regexp = "^[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}$", message = "Insira o seu cpf corretamente")
	private String cpf;
	
	@Pattern(regexp = "^[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}$", message = "Insira o seu rg corretamente.")
	private String rg;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private Serie serie;
	
	@Enumerated(EnumType.STRING)
	private Turma turma;
	
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	@JsonFormat(pattern = "yyyy")
	@PastOrPresent(message = "Data inválida")
	private Date ano;

	public Aluno() {
		super();
	}

	public Aluno(Long id, String nome_completo, Integer idade, Genero genero, String cpf, String rg, Endereco endereco,
			Serie serie, Turma turma, Turno turno, Date ano) {
		super();
		this.id = id;
		this.nome_completo = nome_completo;
		this.idade = idade;
		this.genero = genero;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.serie = serie;
		this.turma = turma;
		this.turno = turno;
		this.ano = ano;
	}

	public Aluno(String nome_completo, Integer idade, Genero genero, String cpf, String rg, Endereco endereco,
			Serie serie, Turma turma, Turno turno, Date ano) {
		super();
		this.nome_completo = nome_completo;
		this.idade = idade;
		this.genero = genero;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.serie = serie;
		this.turma = turma;
		this.turno = turno;
		this.ano = ano;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, cpf, endereco, genero, id, idade, nome_completo, rg, serie, turma, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(ano, other.ano) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(endereco, other.endereco) && genero == other.genero && Objects.equals(id, other.id)
				&& Objects.equals(idade, other.idade) && Objects.equals(nome_completo, other.nome_completo)
				&& Objects.equals(rg, other.rg) && serie == other.serie && turma == other.turma && turno == other.turno;
	}

}
