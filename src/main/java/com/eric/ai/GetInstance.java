package com.eric.ai;

import com.eric.ai.domain.Instance;
import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.ItemService;

public class GetInstance {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        final String PROVIDER = "opensource";

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        ItemService.getItemStream(mastersRecordDto, FILE_FOLDER, CHILD_FOLDER)
                .filter(item -> item.containsInstancesForProvider(PROVIDER))
                .forEach(item -> {
                    item.display();
                    System.out.print(PROVIDER.toUpperCase() + ": ");
                    item.getInstances().stream()
                            .filter(inst -> inst.getProvider().equals(PROVIDER))
                            .forEach(Instance::displayProducts);
                    System.out.println();
                });

    }
}
