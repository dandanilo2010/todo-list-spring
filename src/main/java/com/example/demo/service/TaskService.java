package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO criarTarefa(TaskDTO dto){
        TaskEntity task = new TaskEntity();

        task.setTitulo(dto.getTitulo());
        task.setDescricao(dto.getDescricao());
        task.setConcluida(false);
        task.setDataCriacao(LocalDateTime.now());

        TaskEntity salvo = taskRepository.save(task);

        return new TaskDTO(salvo);
    }

    public List<TaskDTO> listarTarefa(){
        return taskRepository.findAll()
                .stream()
                .map(TaskDTO:: new)
                .collect(Collectors.toList());
    }

    public TaskDTO buscarPorIdDTO(Long id){
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));

        return new TaskDTO(task);
    }

    public TaskDTO atualizarDTO(Long id, TaskDTO taskAtualizada){
        TaskEntity taskExistente = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));

        taskExistente.setTitulo(taskAtualizada.getTitulo());
        taskExistente.setDescricao(taskAtualizada.getDescricao());

        TaskEntity atualizado = taskRepository.save(taskExistente);

        return new TaskDTO(atualizado);
    }

    public void deletar(Long id){
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));

        taskRepository.delete(task);
    }

    public TaskDTO concluirTarefa(Long id){
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));

        task.setConcluida(true);

        TaskEntity atualizado = taskRepository.save(task);

        return new TaskDTO(atualizado);
    }

    public List<TaskDTO> buscarConcluidas(){

        return taskRepository.findByConcluidaTrue()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> buscarPendentes(){

        return taskRepository.findByConcluidaFalse()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }
}
