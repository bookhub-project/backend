package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@SqlResultSetMapping(
//        name="UsuarioEstanteResponseVO",
//        classes={
//                @ConstructorResult(
//                        targetClass=com.bookhub.backendbookhub.api.vo.UsuarioEstanteResponseVO.class,
//                        columns={
//                                @ColumnResult(name="l.id"),
//                                @ColumnResult(name="l.nome"),
//                                @ColumnResult(name="l.autor"),
//                                @ColumnResult(name="l.descricao"),
//                                @ColumnResult(name="l.url_livro"),
//                                @ColumnResult(name="l.n_paginas"),
//                                @ColumnResult(name="ae.nota"),
//                                @ColumnResult(name="ae.lido"),
//                                @ColumnResult(name="ae.comprado"),
//                                @ColumnResult(name="ae.id_usuario")
//                        }
//                )
//        }
//)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario_estante")
public class UsuarioEstanteEntity {


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_livro")
    private Integer idLivro;

    @Column
    private Boolean lido;

    @Column
    private Boolean comprado;

    @Column
    private Integer nota;

}
