package com.catalog.services;

import com.catalog.dto.CategoryDTO;
import com.catalog.dto.ClientsDTO;
import com.catalog.entities.Clients;
import com.catalog.repositories.ClientsRepository;
import com.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Transactional(readOnly = true)
    public Page<ClientsDTO> findAll(PageRequest pageRequest) {
        Page<Clients> list = clientsRepository.findAll(pageRequest);
        return list.map(client -> new ClientsDTO(client));
    }

    @Transactional
    public ClientsDTO insert(ClientsDTO dto) {
        Clients clients = new Clients();
        clients.setName(dto.getName());
        clients.setBirthDate(dto.getBirthDate());
        clients.setChildren(dto.getChildren());
        clients.setIncome(dto.getIncome());
        clients.setCpf(dto.getCpf());

        clients = clientsRepository.save(clients);
        return new ClientsDTO(clients);
    }

    @Transactional(readOnly = true)
    public ClientsDTO findById(Long id) {
        Optional<Clients> obj = clientsRepository.findById(id);
        Clients entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientsDTO(entity);
    }

    @Transactional
    public ClientsDTO update(ClientsDTO dto, Long id) {
        try {
            Clients entity = clientsRepository.getById(id);
            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf());
            entity.setChildren(dto.getChildren());
            entity.setIncome(dto.getIncome());
            entity.setBirthDate(dto.getBirthDate());

            entity = clientsRepository.save(entity);
            return new ClientsDTO(entity);

        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void remove(Long id){
        try{
            clientsRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }
}
