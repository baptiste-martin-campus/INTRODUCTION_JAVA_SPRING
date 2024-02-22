package fr.le_campus_numerique.intro_java_spring.controllers;

import fr.le_campus_numerique.intro_java_spring.dto.GameCreationParams;
import fr.le_campus_numerique.intro_java_spring.dto.GameDTO;
import fr.le_campus_numerique.intro_java_spring.dto.GameInfo;
import fr.le_campus_numerique.intro_java_spring.dto.TokenDTO;
import fr.le_campus_numerique.intro_java_spring.interfaces.GamePlugin;
import fr.le_campus_numerique.intro_java_spring.interfaces.GameService;
import fr.le_campus_numerique.intro_java_spring.services.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameServiceImpl gameServiceImpl;

    private GameDTO gameToDTO(Game game) {
        return new GameDTO(game.getId().toString(),game.getBoard(), game.getStatus(), game.getRemainingTokens());
    }

    // Recupère la liste des jeux
    @GetMapping("/games")
    public Collection<GameDTO> getGames(){
        return gameService.findAllGames().stream().map(this::gameToDTO).toList();
    }

    // Créer un jeu
    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {
        Game game = gameService.createGame(params.getTypeOfGame(), params.getBoardSize(), params.getPlayerCount());
        return gameToDTO(game);
    }

    // Récupère une partie déjà créée
    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable String gameId) {
        return gameToDTO(gameService.findOneById(gameId));
    }

    // Modifie une partie déjà créée
    @PutMapping("/games/{gameId}")
    public GameDTO updateGame(@PathVariable String gameId, @RequestBody GameCreationParams params){
        return gameToDTO(gameService.update(gameId, params));
    }

    // Supprime une partie déjà créée
    @DeleteMapping("/games/{gameId}")
    public String deleteGame(@PathVariable String gameId){
        gameService.delete(gameId);
        return gameId + " removed";
    }

    // Déplace une pièce
    @PutMapping("/games/{gameId}/positions")
    public GameDTO move(@PathVariable String gameId, @RequestBody TokenDTO tokenDTO) throws InvalidPositionException {
        return gameToDTO(gameService.move(gameId, tokenDTO));
    }

    @GetMapping("/catalog")
    public List<GameInfo> getGameCatalog(@RequestHeader("Accept-Language") Locale locale) {
        List<GameInfo> gameInfos = new ArrayList<>();
        for (GamePlugin gamePlugin : gameServiceImpl.getGamePlugins()) {
            gameInfos.add(new GameInfo(gamePlugin.getName(locale), gamePlugin.getPlayerCount()));
        }
        return gameInfos;
    }

}
