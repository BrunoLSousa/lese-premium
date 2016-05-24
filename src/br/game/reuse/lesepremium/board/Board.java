/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.board;

import br.game.reuse.lesepremium.house.House;
import br.game.reuse.lesepremium.model.Teacher;
import br.game.reuse.lesepremium.model.dao.PlayerDAO;
import br.game.reuse.lesepremium.model.dao.TeacherDAO;
import br.game.reuse.lesepremium.question.ProjectBoard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author cass
 */
public class Board {

    private static Board instance;
    private ArrayList<House> houses;
    private HashMap<String, PlayerBoard> players;
    private ProjectBoard project;

    private Board() {
        this.houses = new ArrayList<>();
        this.players = new HashMap<>();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public House getHouseAtIndex(int i) {
        if (i >= this.houses.size()) {
            return null;
        }
        return this.houses.get(i);
    }

    public int addHouse(House house) {
        this.houses.add(house);
        return this.houses.size() - 1;
    }

    public void addPlayer(String nickname, String pawnColor, String idPlayer) {
        PlayerBoard newPlayer = new PlayerBoard(nickname);
        this.players.put(idPlayer, newPlayer);
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> playersArray = new ArrayList<>();
        for (Entry<String, PlayerBoard> item : this.players.entrySet()) {
            playersArray.add(item.getKey());
        }
        return playersArray;
    }

    public PlayerBoard getPlayer(String id) {
        return this.players.get(id);
    }

    private int getPlayerHouseId(PlayerBoard p) {
        return p.getCurrentPos();
    }

    public House getPlayerHouse(PlayerBoard p) {
        return getHouseAtIndex(getPlayerHouseId(p));
    }

    public void storePlayers() {
        for (Entry<String, PlayerBoard> item : this.players.entrySet()) {
            PlayerBoard player = item.getValue();
            new PlayerDAO().createPlayer(player);
        }
    }

    public boolean hasWinner() {
        int finalHouseId = this.houses.size() - 1;
        for (String p : getPlayers()) {
            PlayerBoard player = this.players.get(p);
            if (getPlayerHouseId(player) == finalHouseId) {
                return true;
            }
        }
        return false;
    }

    public String getWinner() {
        if (hasWinner()) {
            int finalHouseId = this.houses.size() - 1;
            for (String p : getPlayers()) {
                PlayerBoard player = this.players.get(p);
                if (getPlayerHouseId(player) == finalHouseId) {
                    return p;
                }
            }
        }
        return null;
    }

    public void createProjectBoard(String name, String description, int cycle) {
        this.project = new ProjectBoard(name, description, cycle);
    }

    public ProjectBoard getProjectBoard() {
        return this.project;
    }
}
