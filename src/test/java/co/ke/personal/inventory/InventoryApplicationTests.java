package co.ke.personal.inventory;

import co.ke.personal.inventory.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class InventoryApplicationTests {
    @Autowired
    ItemService itemService;

    @Test
    void itShouldGetPreviousWeekStartAndEndDate(){
        //given
        LocalDate today =  LocalDate.of(2022,11,28);
        //when
        Map<String, LocalDate> map = itemService.getPreviousWeek(today);
        LocalDate startDate = map.get("SUNDAY");
        LocalDate endDate =  map.get("SATURDAY");
        //then
        assertThat(startDate==LocalDate.of(2022,11,20));
        assertThat(endDate==LocalDate.of(2022,11,26));
    }

    @Test
    void itShouldGetPreviousMonthStartAndEndDate(){
        //given
        LocalDate today =  LocalDate.of(2022,11,28);
        //when
        Map<String, LocalDate>map = itemService.getPreviousMonth(today);
        LocalDate startDate = map.get("START_OF_MONTH");
        LocalDate endDate =  map.get("END_OF_MONTH");
        //then
        assertThat(startDate==LocalDate.of(2022,10,1));
        assertThat(endDate==LocalDate.of(2022,10,31));
    }


}
