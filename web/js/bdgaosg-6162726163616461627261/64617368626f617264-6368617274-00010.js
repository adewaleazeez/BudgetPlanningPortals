/**
 * Method for making charts for selected values
 * 
 * @param budgetLineTitle
 * @param chartData
 * @returns
 */
function makeBudgetLineChart(budgetLineTitle, chartData, forecast_method) {
	"use strict";
	
	//get chosen method
	var chosen_method = '';
	
	if(forecast_method === 1)
		chosen_method = ' >> Own Percentages';
	else if(forecast_method === 2)
		chosen_method = ' >> Elasticity';
	else if(forecast_method === 3)
		chosen_method = ' >> 3-Year Moving Average';
	else if(forecast_method === 4)
		chosen_method = ' >> 5-Year Moving Average X-Outliers';
	else if(forecast_method === 5)
		chosen_method = ' >> 4-Year Moving Average Weighted';
	
	//set chart subtitle
	$('#modal_chart').iziModal('setSubtitle', budgetLineTitle + " Forecast" + chosen_method);
    
    // chart
    var chart = AmCharts.makeChart("budget-line-chart", {
        "type": "serial",
        "theme": "light",
        "fontFamily": "Poppins",
        "marginTop":0,
        "marginRight": 30,
        "legend": {
        	"horizontalGap": 10,
            "maxColumns": 5,
            "position": "bottom",
    		"useGraphSettings": true,
    		"markerSize": 10
        },
        "dataProvider": chartData,
        "valueAxes": [{
            "axisAlpha": 0,
            "position": "left",
            "title": budgetLineTitle
        }],
        "graphs": [{
            "id":"g1",
            "balloonText": "[[category]]<br><b><span style='font-size:14px;'>" + budgetLineTitle + ": [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#27ae60",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": chosen_method.substring(4),
            "valueField": "BudgetValue",
        }],
        "chartScrollbar": {
            "graph":"g1",
            "gridAlpha":0,
            "color":"#888888",
            "scrollbarHeight":55,
            "backgroundAlpha":0,
            "selectedBackgroundAlpha":0.1,
            "selectedBackgroundColor":"#888888",
            "graphFillAlpha":0,
            "autoGridCount":true,
            "selectedGraphFillAlpha":0,
            "graphLineAlpha":0.2,
            "graphLineColor":"#c2c2c2",
            "selectedGraphLineColor":"#888888",
            "selectedGraphLineAlpha":1
        },
        "chartCursor": {
            "categoryBalloonDateFormat": "YYYY",
            "cursorAlpha": 0,
            "valueLineEnabled":true,
            "valueLineBalloonEnabled":true,
            "valueLineAlpha":0.5,
            "fullWidth":true
        },
        "dataDateFormat": "YYYY",
        "categoryField": "year",
        "categoryAxis": {
            "minPeriod": "YYYY",
            "parseDates": true,
            "minorGridAlpha": 0.1,
            "minorGridEnabled": true
        },
        "export": {
            "enabled": true
        }
    });
    
    chart.addListener("rendered", zoomChart);
    
    if(chart.zoomChart)
    	zoomChart(chart);
}

/**
 * Method for making charts for budget and actuals
 * 
 * @param budgetLineTitle
 * @param chartData
 * @returns
 */
function makeBudgetLineChart2(budgetLineTitle, chartData, forecast_method) {
	"use strict";
	
	//get chosen method
	var chosen_method = '';
	
	if(forecast_method === 1)
		chosen_method = ' >> Own Percentages';
	else if(forecast_method === 2)
		chosen_method = ' >> Elasticity';
	else if(forecast_method === 3)
		chosen_method = ' >> 3-Year Moving Average';
	else if(forecast_method === 4)
		chosen_method = ' >> 5-Year Moving Average X-Outliers';
	else if(forecast_method === 5)
		chosen_method = ' >> 4-Year Moving Average Weighted';
	
	//set chart subtitle
	$('#modal_chart').iziModal('setSubtitle', budgetLineTitle + " Forecast" + chosen_method + " >> Budget Vs Actual");
    
    // chart
    var chart = AmCharts.makeChart("budget-line-chart", {
        "type": "serial",
        "theme": "light",
        "fontFamily": "Poppins",
        "marginTop":0,
        "marginRight": 30,
        "legend": {
        	"horizontalGap": 10,
            "maxColumns": 2,
            "position": "bottom",
    		"useGraphSettings": true,
    		"markerSize": 10
        },
        "dataProvider": chartData,
        "valueAxes": [{
            "axisAlpha": 0,
            "position": "left",
            "title": budgetLineTitle
        }],
        "graphs": [{
            "id":"g1",
            "balloonText": "[[category]]<br><b><span style='font-size:14px;'>Budget: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#337ab7",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "Budget",
            "valueField": "BudgetValue",
        }, {
            "id": "g2",
            "balloonText": "[[category]]<br><b><span style='font-size:14px;'>Actual: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#27ae60",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "Actual",
            "valueField": "ActualValue",
        }],
        "chartScrollbar": {
            "graph":"g1",
            "gridAlpha":0,
            "color":"#888888",
            "scrollbarHeight":55,
            "backgroundAlpha":0,
            "selectedBackgroundAlpha":0.1,
            "selectedBackgroundColor":"#888888",
            "graphFillAlpha":0,
            "autoGridCount":true,
            "selectedGraphFillAlpha":0,
            "graphLineAlpha":0.2,
            "graphLineColor":"#c2c2c2",
            "selectedGraphLineColor":"#888888",
            "selectedGraphLineAlpha":1
        },
        "chartCursor": {
            "categoryBalloonDateFormat": "YYYY",
            "cursorAlpha": 0,
            "valueLineEnabled":true,
            "valueLineBalloonEnabled":true,
            "valueLineAlpha":0.5,
            "fullWidth":true
        },
        "dataDateFormat": "YYYY",
        "categoryField": "year",
        "categoryAxis": {
            "minPeriod": "YYYY",
            "parseDates": true,
            "minorGridAlpha": 0.1,
            "minorGridEnabled": true
        },
        "export": {
            "enabled": true
        }
    });
    
    chart.addListener("rendered", zoomChart);
    
    if(chart.zoomChart)
    	zoomChart(chart);
}

/**
 * Method for making charts for all forecast options
 * 
 * @param budgetLineTitle
 * @param chartData
 * @returns
 */
function makeBudgetLineChart3(budgetLineTitle, chartData) {
	"use strict";
	
	//set chart subtitle
	$('#modal_chart').iziModal('setSubtitle', budgetLineTitle + " Forecast Options");
	
    // chart
    var chart = AmCharts.makeChart("budget-line-chart", {
        "type": "serial",
        "theme": "light",
        "fontFamily": "Poppins",
        "marginTop":0,
        "marginRight": 30,
        "legend": {
        	"horizontalGap": 10,
            "maxColumns": 5,
            "position": "bottom",
    		"useGraphSettings": true,
    		"markerSize": 10
        },
        "dataProvider": chartData,
        "valueAxes": [{
            "axisAlpha": 0,
            "position": "left",
            "title": budgetLineTitle
        }],
        "graphs": [{
            "id":"g1",
            "balloonText": "<b><span style='font-size:14px;'>Own % Increase: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#27ae60",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "Own % Increase",
            "valueField": "OwnPercentageValue",
        }, {
            "id": "g2",
            "balloonText": "<b><span style='font-size:14px;'>Elasticity: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#FFC001",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "Elasticity",
            "valueField": "ElasticityValue",
        }, {
            "id": "g3",
            "balloonText": "<b><span style='font-size:14px;'>MA 3-Year: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#3dd1d1",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "MA 3-Year",
            "valueField": "MAThreeValue",
        }, {
            "id": "g4",
            "balloonText": "<b><span style='font-size:14px;'>MA 5-Year XO: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#5677fc",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "MA 5-Year XO",
            "valueField": "MAFiveValue",
        }, {
            "id": "g4",
            "balloonText": "<b><span style='font-size:14px;'>MA 4-Year Weighted: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#d90000",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "title": "MA 4-Year Weighted",
            "valueField": "MAFourValue",
        }],
        "chartScrollbar": {
            "graph":"g1",
            "gridAlpha":0,
            "color":"#888888",
            "scrollbarHeight":55,
            "backgroundAlpha":0,
            "selectedBackgroundAlpha":0.1,
            "selectedBackgroundColor":"#888888",
            "graphFillAlpha":0,
            "autoGridCount":true,
            "selectedGraphFillAlpha":0,
            "graphLineAlpha":0.2,
            "graphLineColor":"#c2c2c2",
            "selectedGraphLineColor":"#888888",
            "selectedGraphLineAlpha":1
        },
        "chartCursor": {
            "categoryBalloonDateFormat": "YYYY",
            "cursorAlpha": 0,
            "valueLineEnabled":true,
            "valueLineBalloonEnabled":true,
            "valueLineAlpha":0.5,
            "fullWidth":true
        },
        "dataDateFormat": "YYYY",
        "categoryField": "year",
        "categoryAxis": {
            "minPeriod": "YYYY",
            "parseDates": true,
            "minorGridAlpha": 0.1,
            "minorGridEnabled": true
        },
        "export": {
            "enabled": true
        }
    });
    
    chart.addListener("rendered", zoomChart);
    
    if(chart.zoomChart)
    	zoomChart(chart);
}

function zoomChart(chart){
    chart.zoomToIndexes(Math.round(chart.dataProvider.length * 4), Math.round(chart.dataProvider.length * 10));
}