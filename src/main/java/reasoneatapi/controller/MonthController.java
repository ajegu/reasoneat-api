package reasoneatapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reasoneatapi.dto.MonthDTO;
import reasoneatapi.service.MonthService;

import java.util.List;

@RestController
@RequestMapping("/months")
@Api(tags = {"Mois"})
public class MonthController {

    private final MonthService monthService;

    public MonthController(MonthService monthService) {
        this.monthService = monthService;
    }

    @GetMapping
    @ApiOperation("Lister les mois")
    public List<MonthDTO> list() {
        return monthService.findAll();
    }
}
