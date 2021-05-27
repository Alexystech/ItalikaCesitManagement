/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service;

import com.itsx.slasher.italikacesitmanagement.model.Client;
import java.util.List;

/**
 *
 * @author defin
 */
public interface ClientService {
    boolean createClient(Client client);
    boolean removeClientByFolio(long folio);
    boolean updateClient(Client client);
    Client getClientByFolio(long folio);
    List<Client> getAllClients();
}
