/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.board;

import game.lese.house.House;
import game.lese.model.Teacher;
import game.lese.model.dao.PlayerDAO;
import game.lese.model.dao.TeacherDAO;
import game.lese.question.ProjectBoard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author cass
 */
public class Board {

    private static Board instance;
    private List<House> houses;
    private List<PlayerBoard> players;
    private ProjectBoard project;

    private Board() {
        this.houses = new ArrayList<>();
        this.players = new ArrayList<>();
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
        this.players.add(new PlayerBoard(nickname));
    }

    public List<PlayerBoard> getPlayers() {
        return this.players;
    }

    private int getPlayerHouseId(PlayerBoard p) {
        return p.getCurrentPos();
    }

    public House getPlayerHouse(PlayerBoard p) {
        return getHouseAtIndex(getPlayerHouseId(p));
    }

    public void storePlayers() {
        for (PlayerBoard p : this.players) {
            new PlayerDAO().createPlayer(p);
        }
    }

    public boolean hasWinner() {
        int finalHouseId = this.houses.size() - 1;
        for (PlayerBoard player : this.players) {
            if (getPlayerHouseId(player) == finalHouseId) {
                return true;
            }
        }
        return false;
    }

    public String getWinner() {
        if (hasWinner()) {
            int finalHouseId = this.houses.size() - 1;
            for (PlayerBoard player : this.players) {
                if (getPlayerHouseId(player) == finalHouseId) {
                    return player.getNickname();
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
    
    public void destroyPlayers(){
        this.players.clear();
    }
}
