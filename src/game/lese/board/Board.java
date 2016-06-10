/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.board;

import game.lese.house.House;
import game.lese.model.dao.PlayerDAO;
import game.lese.question.ProjectInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cass
 */
public class Board {

    private static Board instance;
    private List<House> houses;
    private final List<PlayerBoard> players;
    private ProjectInfo project;

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

    public void addPlayer(String nickname) {
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
            PlayerDAO.createPlayer(p);
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

    public void setProject(ProjectInfo p) {
        this.project = p;
    }

    public ProjectInfo getProject() {
        return this.project;
    }
    
    public void destroyPlayers(){
        this.players.clear();
    }
}
