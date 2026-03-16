package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> criar(@RequestBody TaskDTO taskDTO){
        TaskDTO taskSalva = taskService.criarTarefa(taskDTO);
        return ResponseEntity.status(201).body(taskSalva);

    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> listarTarefas(){
        List <TaskDTO> tarefas = taskService.listarTarefa();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> buscarPorId(@PathVariable Long id){

        TaskDTO tarefa = taskService.buscarPorIdDTO(id);

        return ResponseEntity.ok(tarefa);
    }

    @GetMapping("/concluidas")
    public ResponseEntity<List<TaskDTO>> listarConcluidas(){

        List<TaskDTO> tarefas = taskService.buscarConcluidas();

        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<TaskDTO>> listarPendentes(){

        List<TaskDTO> tarefas = taskService.buscarPendentes();

        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> atualizar(@PathVariable Long id,
                                             @RequestBody TaskDTO dto){

        TaskDTO atualizado = taskService.atualizarDTO(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        taskService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<TaskDTO> concluir(@PathVariable Long id){

        TaskDTO tarefa = taskService.concluirTarefa(id);

        return ResponseEntity.ok(tarefa);
    }
}