package cn.ssmc.terms.service;

import cn.ssmc.entity.Terms;

public interface TermService {

    Terms selectNow();
    Terms selectBefore1();
}
