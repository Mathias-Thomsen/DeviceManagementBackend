package com.example.devicemanagement.services.idService;


import com.example.devicemanagement.enums.SimCardType;

public class SimCardIdService {


    /*
    @Autowired
    private SimCardRepository simCardRepository;



    public String generateNextSimCardId(SimCardType simCardType) {
        String simCardTypeAbbreviation = simCardType.getAbbreviation();

        // Find det højeste eksisterende ID for den pågældende enhedstype
        String maxSimCardId = simCardRepository.findMaxDeviceIdForType(simCardTypeAbbreviation);

        int abbreviationLength = simCardTypeAbbreviation.length();

        // Hent den numeriske sekvens fra eksisterende ID, hvis det findes
        int nextSequence = 1;
        if (maxSimCardId != null && maxSimCardId.length() > abbreviationLength) {
            String sequencePart = maxSimCardId.substring(abbreviationLength);
            nextSequence = Integer.parseInt(sequencePart) + 1;
        }

        // Formater sekvensen med førende nuller og sammensæt ID
        String formattedSequence = String.format("%0" + (8 - abbreviationLength) + "d", nextSequence);
        return simCardTypeAbbreviation + formattedSequence;
    }

     */



}
