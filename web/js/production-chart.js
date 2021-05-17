$(function(){
    "use strict";
    
    // chart
    var chart = AmCharts.makeChart("production-chart", {
        "type": "serial",
        "theme": "light",
        "fontFamily": "Poppins",
        "marginTop":0,
        "marginRight": 30,
        "dataProvider": [ {
                "year": "2011",
                "Agric": 0.39,
                "Health": 0.39
        }, {
                "year": "2012",
                "Agric": 0.37,
                "Health": 0.29
        }, {
                "year": "2013",
                "Agric": 0.35,
                "Health": 0.29
        }, {
                "year": "2014",
                "Agric": -0.21,
                "Health": -0.16
        }, {
                "year": "2015",
                "Agric": 0.30,
                "Health": 0.24
        }, {
                "year": "2016",
                "Agric": 0.25,
                "Health": 0.18
        }, {
                "year": "2017",
                "Agric": 0.38,
                "Health": 0.29
        }, {
                "year": "2018",
                "Agric": 0.01,
                "Health": 0.30
        }, {
            "year": "2019",
            "Agric": 0.44,
            "Health": 0.12
        }, {
            "year": "2020",
            "Agric": 0.47,
            "Health": 0.36
        }],
        "valueAxes": [{
            "axisAlpha": 0,
            "position": "left",
            "title": "3 Year Moving Average"
        }],
        "graphs": [{
            "id":"g1",
            "balloonText": "[[category]]<br><b><span style='font-size:14px;'>Agriculture: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#27ae60",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "valueField": "Agric",
        }, {
            "id": "g2",
            "balloonText": "[[category]]<br><b><span style='font-size:14px;'>Health: [[value]]</span></b>",
            "bullet": "round",
            "bulletSize": 8,
            "lineColor": "#337ab7",
            "lineThickness": 2,
            "negativeLineColor": "#e74c3c",
            "type": "smoothedLine",
            "valueField": "Health",
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
    if(chart.zoomChart){
        chart.zoomChart();
    }

    function zoomChart(){
        chart.zoomToIndexes(Math.round(chart.dataProvider.length * 0.2), Math.round(chart.dataProvider.length * 0.9));
    }



    var ceilingChart = AmCharts.makeChart("ceiling-sector", {
        "type": "serial",
        "theme": "light",
        "dataProvider": [{
            "sector": "Agric",
            "ceiling": 2025
        }, {
            "sector": "Health",
            "ceiling": 1882
        }, {
            "sector": "Edu ",
            "ceiling": 1809
        }, {
            "sector": "Tourism",
            "ceiling": 1322
        }, {
            "sector": "Cult",
            "ceiling": 1722
        }, {
            "sector": "Infra",
            "ceiling": 2522
        }, {
            "sector": "Works",
            "ceiling": 2322
        }],
        "valueAxes": [{
            "gridColor": "#FFFFFF",
            "gridAlpha": 0.2,
            "dashLength": 0
        }],
        "gridAboveGraphs": true,
        "startDuration": 1,
        "graphs": [{
            "balloonText": "[[category]]: <b>[[value]]</b>",
            "fillAlphas": 0.8,
            "lineAlpha": 0.2,
            "type": "column",
            "valueField": "ceiling"
        }],
        "chartCursor": {
            "categoryBalloonEnabled": false,
            "cursorAlpha": 0,
            "zoomable": false
        },
        "categoryField": "sector",
        "categoryAxis": {
            "gridPosition": "start",
            "gridAlpha": 0,
            "tickPosition": "start",
            "tickLength": 20
        },
        "export": {
            "enabled": true
        }

    });



    var completionChart = AmCharts.makeChart("completion-status", {
        "type": "pie",
        "theme": "none",
        "titles": [{
            "text": "Year 2017",
            "size": 16
        }],
        "dataProvider": [{
            "sector": "Health",
            "completion_rate": 10
        }, {
            "sector": "Education",
            "completion_rate": 10
        }, {
            "sector": "Agriculture",
            "completion_rate": 12
        }, {
            "sector": "Commerce",
            "completion_rate": 5
        }, {
            "sector": "Finance",
            "completion_rate": 9
        }, {
            "sector": "Works",
            "completion_rate": 2
        }, {
            "sector": "Sports",
            "completion_rate": 15
        }, {
            "sector": "Housing",
            "completion_rate": 40
        }],
        "valueField": "completion_rate",
        "titleField": "sector",
        "startEffect": "elastic",
        "startDuration": 2,
        "labelRadius": 15,
        "innerRadius": "50%",
        "depth3D": 10,
        "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
        "angle": 15,
        "export": {
            "enabled": true
        }
    });

    var spendingChart = AmCharts.makeChart("spending-chart", {
        "type": "serial",
        "theme": "none",
        "depth3D": 20,
        "angle": 30,
        "legend": {
            "horizontalGap": 10,
            "useGraphSettings": true,
            "markerSize": 10
        },
        "dataProvider": [{
            "policy": "Free Education",
            "Agric": 2.5,
            "Health": 2.5,
            "Education": 3.1,
            "Transport": 1.2,
            "Youth": 2.2,
            "Environment": 0.1
        }, {
            "policy": "15% Boost in Food Production",
            "Agric": 3.6,
            "Health": 1.7,
            "Education": 2.2,
            "Transport": 1.3,
            "Youth": 0.3,
            "Environment": 0.1
        }, {
            "policy": "30% increase in tourism",
            "Agric": 0.8,
            "Health": 2.9,
            "Education": 2.4,
            "Transport": 3.4,
            "Youth": 1.3,
            "Environment": 0.1
        }, {
            "policy": "Kick against Indiscipline",
            "Agric": 0.1,
            "Health": 1.9,
            "Education": 3.4,
            "Transport": 0.4,
            "Youth": 0.3,
            "Environment": 2.1
        }, {
            "policy": "Girl Child Education",
            "Agric": 0.1,
            "Health": 0.9,
            "Education": 3.4,
            "Transport": 0.4,
            "Youth": 2.3,
            "Environment": 0.1
        }],
        "valueAxes": [{
            "stackType": "regular",
            "axisAlpha": 0,
            "gridAlpha": 0,
            "title":"Total Spending"
        }],
        "graphs": [{
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Agriculture",
            "type": "column",
            "color": "#000000",
            "valueField": "Agric"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Health",
            "type": "column",
            "color": "#000000",
            "valueField": "Health"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Education",
            "type": "column",
            "newStack": true,
            "color": "#000000",
            "valueField": "Education"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Transport",
            "type": "column",
            "color": "#000000",
            "valueField": "Transport"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Youth and Sports",
            "type": "column",
            "color": "#000000",
            "valueField": "Youth"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Environment",
            "type": "column",
            "color": "#000000",
            "valueField": "Environment"
        }],
        "categoryField": "policy",
        "categoryAxis": {
            "gridPosition": "start",
            "axisAlpha": 0,
            "gridAlpha": 0,
            "position": "left"
        },
        "export": {
            "enabled": true
        }

    });
});
