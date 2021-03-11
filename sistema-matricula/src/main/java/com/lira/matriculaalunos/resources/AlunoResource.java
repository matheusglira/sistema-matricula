package com.lira.matriculaalunos.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lira.matriculaalunos.models.Aluno;
import com.lira.matriculaalunos.repositories.AlunoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path="/alunos")
public class AlunoResource {
	
	private AlunoRepository alunoRepository;
	
	public AlunoResource(AlunoRepository alunoRepository) {
		super();
		this.alunoRepository = alunoRepository;
	}
	
	@ApiOperation("Cadastro de Alunos")
	@PostMapping
	public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
		alunoRepository.save(aluno);
		return new ResponseEntity<>(aluno, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna uma lista com todos os alunos")
	@GetMapping
	public ResponseEntity<List<Aluno>> getAll(){
		List<Aluno> alunos = new ArrayList<>();
		alunos = alunoRepository.findAll();
		return new ResponseEntity<>(alunos, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna um aluno específico")
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Aluno>> getById(@PathVariable Integer id){
		Optional<Aluno> aluno;
		try {
			aluno = alunoRepository.findById(id);
			return new ResponseEntity<Optional<Aluno>>(aluno, HttpStatus.OK);
		}catch(NoSuchElementException e){
			return new ResponseEntity<Optional<Aluno>>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation("Deleta um aluno")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Aluno>> deleteById(@PathVariable Integer id){
		try {
			alunoRepository.deleteById(id);
			return new ResponseEntity<Optional<Aluno>>(HttpStatus.OK);
		}catch(NoSuchElementException e){
			return new ResponseEntity<Optional<Aluno>>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation("Atualiza dados de um aluno")
	@PutMapping(value="/{id}")
	public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno newAluno){
		return alunoRepository.findById(id)
				.map(aluno ->{
					aluno.setNome(newAluno.getNome());
					aluno.setSobrenome(newAluno.getSobrenome());
					Aluno alunoUpdate = alunoRepository.save(aluno);
					return ResponseEntity.ok().body(alunoUpdate);
				}).orElse(ResponseEntity.notFound().build());
	}
}
