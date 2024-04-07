package org.portfolio.homeservice.business.rules;

import org.portfolio.homeservice.business.messages.Messages;
import org.portfolio.homeservice.utils.exceptions.BusinessException;

public class HomeRules {
    public static void checkIfIdIsValid(Long id){
        if(id==null||id<=0)
            throw new BusinessException(Messages.invalidHomeIdException);
    }

    public static void checkPageNumberAndPageSize(Integer pageNumber,Integer pageSize){
        if(pageNumber==null||pageNumber<0)
            throw new BusinessException(Messages.invalidPageNumberException);
        else if (pageSize==null||pageSize<=0)
            throw new BusinessException(Messages.invalidPageSizeException);
    }
}
