package al_hiro.com.Mkoba.Management.System.controller;

import al_hiro.com.Mkoba.Management.System.dto.DividendDto;
import al_hiro.com.Mkoba.Management.System.entity.YearlyDividend;
import al_hiro.com.Mkoba.Management.System.service.YearlyDividendService;
import al_hiro.com.Mkoba.Management.System.utils.PageableParam;
import al_hiro.com.Mkoba.Management.System.utils.Response;
import al_hiro.com.Mkoba.Management.System.utils.ResponsePage;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.Month;

@Controller
@RequiredArgsConstructor
@GraphQLApi
public class YearlyDividendController {

    private final YearlyDividendService yearlyDividendService;

    @GraphQLMutation(name = "saveYearlyDividend", description = "Saving Member Yearly Dividend")
    public Response<YearlyDividend> saveYearlyDividend(@GraphQLArgument(name = "dividendDto") DividendDto dividendDto) {
        return yearlyDividendService.saveYearlyDividend(dividendDto);
    }

    @GraphQLQuery(name = "getYearlyDividendByMember", description = "Getting a page of yearly dividend by a specific member")
    public ResponsePage<YearlyDividend> getYearlyDividendByMember(@GraphQLArgument(name = "memberId") Long memberId,
                                              @GraphQLArgument(name = "pageableParam") PageableParam pageableParam,
                                              @GraphQLArgument(name = "year") Integer year) {
        return yearlyDividendService.getYearlyDividendByMember(memberId, pageableParam!=null?pageableParam:new PageableParam(), year);
    }

    @GraphQLMutation(name = "deleteYearlyDividend", description = "Delete a yearly dividend by ID")
    public Response<YearlyDividend> deleteYearlyDividend(@GraphQLArgument(name = "id") Long id) {
        return yearlyDividendService.deleteYearlyDividend(id);
    }

    @GraphQLMutation(name = "approveDividend", description = "Approving yearly dividend for member")
    public Response<YearlyDividend> approveDividend(@GraphQLArgument(name = "yearlyDividendId") Long yearlyDividendId) {
        return yearlyDividendService.approveDividend(yearlyDividendId);
    }

    @GraphQLQuery(name = "getYearlyDividends", description = "Getting list of members' yearly dividends")
    public ResponsePage<YearlyDividend> getYearlyDividends(@GraphQLArgument(name = "pageableParam")PageableParam pageableParam){
        return yearlyDividendService.getYearlyDividends(pageableParam!=null?pageableParam:new PageableParam());
    }

    @GraphQLQuery(name = "getTotalDividends", description = "Getting Group's total dividends")
    public Response<Double> getTotalDividends(@GraphQLArgument(name = "year") Integer year) {
        Double totalDividends = yearlyDividendService.getTotalDividends(year);
        return new Response<>(totalDividends);
    }
}
