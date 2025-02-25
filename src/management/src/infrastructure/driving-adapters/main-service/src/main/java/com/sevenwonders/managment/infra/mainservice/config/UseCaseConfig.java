package com.sevenwonders.managment.infra.mainservice.config;


import com.sevenwonders.managment.application.AddCardToWonderUseCase;
import com.sevenwonders.managment.application.card.selectedcard.SelectedCardUseCase;
import com.sevenwonders.managment.application.card.validatecardconstruction.ValidateCardConstructionUseCase;
import com.sevenwonders.managment.application.wonder.assingwonder.AssignWonderUseCase;
import com.sevenwonders.managment.application.wonder.calculatepoints.CalculateWonderPointsUseCase;
import com.sevenwonders.managment.application.wonder.managestage.ManageWonderStageUseCase;
import com.sevenwonders.managment.application.wonder.updateresources.UpdateWonderResourcesUseCase;
import com.sevenwonders.managment.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public AssignWonderUseCase assignWonderUseCase(MongoAdapter adapter){
    return new AssignWonderUseCase(adapter);
  }

  @Bean
  public AddCardToWonderUseCase addCardToWonderUseCase(MongoAdapter adapter){
    return new AddCardToWonderUseCase(adapter);
  }

  @Bean
  public CalculateWonderPointsUseCase calculateWonderPointsUseCase(MongoAdapter adapter){
    return new CalculateWonderPointsUseCase(adapter);
  }

  @Bean
  public ManageWonderStageUseCase manageWonderStageUseCase(MongoAdapter adapter){
    return new ManageWonderStageUseCase(adapter);
  }

  @Bean
  public UpdateWonderResourcesUseCase updateWonderResourcesUseCase(MongoAdapter adapter) {
    return new UpdateWonderResourcesUseCase(adapter);
  }

  @Bean
  public SelectedCardUseCase selectedCardUseCase(MongoAdapter adapter){
    return new SelectedCardUseCase(adapter);
  }

  @Bean
  public ValidateCardConstructionUseCase validateCardConstructionUseCase(MongoAdapter adapter) {
    return new ValidateCardConstructionUseCase(adapter);
  }

}
