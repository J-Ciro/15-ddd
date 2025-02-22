package com.sevenwonders.managment.application.shared.wonder;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.management.domain.wonder.entities.Conflict;
import com.sevenwonders.management.domain.wonder.entities.Stage;
import com.sevenwonders.management.domain.wonder.entities.Vault;

public class WonderMapper {
  public static WonderResponse mapToWonder(Wonder wonder) {
    return new WonderResponse(
      wonder.getIdentity().getValue(),
      wonder.getName().getValue(),
      mapToStage(wonder.getStage()),
      wonder.getMode().getValue(),
      mapToVault(wonder.getVault()),
      mapToConflict(wonder.getConflict()),
      wonder.getCards().stream()
        .map(WonderMapper::mapToCard)
        .toList()
    );
  }

  private static WonderResponse.Card mapToCard(Card card) {
    return new WonderResponse.Card(
      card.getIdentity().getValue(),
      card.getName().getValue(),
      card.getEra().getValue(),
      card.getType().getValue(),
      card.getColor().getValue()
    );
  }

  private static WonderResponse.Stage mapToStage(Stage stage) {
    return new WonderResponse.Stage(
      stage.getName().getValue(),
      stage.getResources().getValue(),
      stage.getStatus().getValue()
    );
  }

  private static WonderResponse.Vault mapToVault(Vault vault) {
    return new WonderResponse.Vault(
      vault.getCoins().getValue(),
      vault.getResources().getValue()
    );
  }

  private static WonderResponse.Conflict mapToConflict(Conflict conflict) {
    return new WonderResponse.Conflict(
      conflict.getMarks().getValue(),
      conflict.getShields().getValue(),
      conflict.getLocation().getValue()
    );
  }
}
