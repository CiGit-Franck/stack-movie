/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Franck
 */
public class JSonService {

    RestTemplate restTemplate;

    /**
     * Valide que le JSon contient le noeud 
     * 
     * @param jsonNode
     * @param nodeText
     * @return 
     */
    boolean checkNode(JsonNode jsonNode, String nodeText) {
        return (jsonNode.has(nodeText)
                && !jsonNode.get(nodeText).isNull()
                && (!jsonNode.get(nodeText).asText().equals("")
                || jsonNode.get(nodeText).size() > 0));
    }
}
