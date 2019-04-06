package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.Setting;
import ch.wintihack.jobinator.persistence.repository.SettingRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {


    private Setting currentSetting;

    @Autowired
    private SettingRepository settingRepository;

    private Setting getFirstSetting() {
        List<Setting> myList = Lists.newArrayList(settingRepository.findAll());
        currentSetting = myList.get(0);
        return currentSetting;
    }

    public Setting getSetting() {
        return currentSetting == null ? getFirstSetting() : currentSetting;
    }
}
