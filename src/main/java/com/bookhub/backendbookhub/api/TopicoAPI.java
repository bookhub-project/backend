package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.api.vo.*;
import com.bookhub.backendbookhub.entity.TopicoComentarioEntity;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import com.bookhub.backendbookhub.service.TopicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@Api(value="Topico",tags = "Topico",description = " ")
public class TopicoAPI {


    @Autowired
    private TopicoService topicoService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva topico",notes = "Insere um tópico no banco de dados", response = TopicoPostResponseVO.class)
    @PostMapping("/topico")
    public ResponseEntity<TopicoPostResponseVO> save(@RequestBody final TopicoPostRequestVO request) {
        TopicoPostResponseVO response = topicoService.save(request);
        return new ResponseEntity<>(response,CREATED);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna um topico buscando por ID", notes = "Retorna um topico buscando por ID")
    @GetMapping("/topico/{id}")
    public TopicoByIdResponseVO find(@ApiParam(example = "10",required = true) @PathVariable("id") final Integer id) {
        return topicoService.find(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna todos os topicos de um usuario especifico", notes = "Retorna todos os topicos de um usuario especifico")
    @GetMapping("/topico/usuario/{idUsuario}")
    public List<TopicoEntity> findByIdUsuario(@ApiParam(example = "10",required = true) @PathVariable("idUsuario") final Integer idUsuario) {
        return topicoService.findByIdUsuario(idUsuario);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna todos os topicos", notes = "Retorna todos os topicos")
    @GetMapping("/topico")
    public List<AllTopicoResponseVO> findAll() {
        return topicoService.findAll();
    }

    @ResponseStatus(ACCEPTED)
    @ApiOperation(value = "Remove um Topico", notes = "Remove um Topico")
    @DeleteMapping("/topico/{idTopico}")
    public ResponseEntity<String> removeTopico(@ApiParam(example = "1",required = true) @PathVariable("idTopico") final Integer idTopico) {
        topicoService.removeTopico(idTopico);
        return new ResponseEntity<>("Removido", ACCEPTED);


    }


    @ResponseStatus(OK)
    @ApiOperation(value = "Altera Topico", notes = "Altera um Topico")
    @PutMapping("/topico")
    public ResponseEntity<String> alteraTopicoComentario(@RequestBody TopicoPutRequestVO topicoPutRequestVO) {
        topicoService.atualizaTopico(topicoPutRequestVO);
        return new ResponseEntity<>("Atualizado", OK);
    }

    // Comentario

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna todos os comentarios de um topicos", notes = "Retorna todos os comentarios de um topicos")
    @GetMapping("/topico/{idTopico}/comentario")
    public List<TopicoComentarioResponseVO> findTopicoComentario(@ApiParam(example = "1",required = true) @PathVariable("idTopico") final Integer idTopico) {
        return topicoService.findTopicoComentario(idTopico);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Insere um comentario no Topico", notes = "Insere um comentario no Topico")
    @PostMapping("/topico/comentario")
    public ResponseEntity<TopicoComentarioEntity> adicionaTopicoComentario(@RequestBody TopicoComentarioPostRequestVO topicoComentarioPostRequestVO) {
        TopicoComentarioEntity response = topicoService.insereTopicoComentario(topicoComentarioPostRequestVO);

        return new ResponseEntity<>(response,CREATED);
    }

    @ResponseStatus(ACCEPTED)
    @ApiOperation(value = "Remove um comentario no Topico", notes = "Remove um comentario no Topico")
    @DeleteMapping("/topico/comentario/{idComentario}")
    public ResponseEntity<String> removeTopicoComentario(@ApiParam(example = "1",required = true) @PathVariable("idComentario") final Integer idComentario) {
        topicoService.removeTopicoComentario(idComentario);
        return new ResponseEntity<>("Removido", ACCEPTED);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Altera um comentario no Topico", notes = "Altera um comentario no Topico")
    @PutMapping("/topico/comentario")
    public ResponseEntity<String> alteraTopicoComentario(@RequestBody TopicoComentarioPutRequestVO comentarioPutRequestVO) {
        topicoService.atualizaTopicoComentario(comentarioPutRequestVO);
        return new ResponseEntity<>("Atualizado", OK);
    }





}
