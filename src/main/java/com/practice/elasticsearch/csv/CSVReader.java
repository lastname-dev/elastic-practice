package com.practice.elasticsearch.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.practice.elasticsearch.feign.CoordinateDto;
import com.practice.elasticsearch.feign.FeignService;
import com.practice.elasticsearch.store.Location;
import com.practice.elasticsearch.store.StoreDto;
import com.practice.elasticsearch.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVReader {

	@Value(value= "${file.path}")
	private String FILE_PATH;

	private final FeignService feignService;
	private final StoreService storeService;
	public void readCSV(String name) {

		try {
			String filePath = new String(FILE_PATH.getBytes("UTF-8"), "UTF-8");
			File file = new File(filePath+name+".csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "EUC-KR"));
			String line;
			while ((line = br.readLine())!=null) {
				List<String> aLine;
				String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
				aLine = Arrays.asList(lineArr);

				if(aLine.get(10).equals("영업")) {
					if(aLine.get(26)=="" || aLine.get(27)==""){
						continue;
					}
					CoordinateDto coordinate = feignService.getCoordinate(Double.parseDouble(aLine.get(26)),
						Double.parseDouble(aLine.get(27)));
					StoreDto store = StoreDto.builder()
						.id(Long.parseLong(aLine.get(0)))
						.location(new Location(coordinate.getDocuments()[0].getY(),coordinate.getDocuments()[0].getX()))
						.address(aLine.get(19))
						.name(aLine.get(21))
						.build();
					storeService.save(store);
				}
			}
			br.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
