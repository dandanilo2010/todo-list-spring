package com.example.demo.dto;

import com.example.demo.entity.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDateTime dataCriacao;


    public TaskDTO (TaskEntity task){
        this.id = task.getId();
        this.titulo = task.getTitulo();
        this.descricao = task.getDescricao();
        this.concluida = task.isConcluida();
        this.dataCriacao = task.getDataCriacao();


    }
}
