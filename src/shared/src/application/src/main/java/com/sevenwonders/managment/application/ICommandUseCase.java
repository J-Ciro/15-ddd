package com.sevenwonders.managment.application;

public interface ICommandUseCase <T extends Request, R>{
  R execute(T request);
}
