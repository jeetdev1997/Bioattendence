/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.service;

import com.junkie.dto.SenderDTO;

/**
 *
 * @author ashish.yetre
 */
public interface ISenderService {
    
    public void send(SenderDTO senderDTO);
}
