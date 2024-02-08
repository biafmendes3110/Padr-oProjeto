package com.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.usuario.DTO.UsuarioDTO;
import com.usuario.entity.Usuario;
import com.usuario.service.UsuarioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private final UsuarioServices usuarioServices;
	
	@Autowired
	public UsuarioController(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid UsuarioDTO usuarioDTO){
		UsuarioDTO salvarUsuario = usuarioServices.salvar(usuarioDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarUsuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO){
		UsuarioDTO alterarUsuarioDTO = usuarioServices.atualizar(id, usuarioDTO);
		if(alterarUsuarioDTO != null) {
			return ResponseEntity.ok(alterarUsuarioDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Usuario> apagar (@PathVariable Long id) {
		boolean apagar = usuarioServices.deletar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId (@PathVariable Long id){
		Usuario usuario = usuarioServices.buscarPorId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscaTodos() {
		List <Usuario> usuario = usuarioServices.buscarTodos();
		return ResponseEntity.ok(usuario);
	}
	

}
