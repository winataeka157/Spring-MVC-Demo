package com.example.bliblispringdemo.command;

import com.example.bliblispringdemo.base.Command;
import com.example.bliblispringdemo.command.model.GetAllParkingLotCommandRequest;
import com.example.bliblispringdemo.controller.model.response.ParkingLotWebResponse;

import java.util.List;

public interface GetAllParkingLotCommand extends Command<GetAllParkingLotCommandRequest, List<ParkingLotWebResponse>> {

}
