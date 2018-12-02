'use strict';

/**
 *
 * @name IoTManagerDashboardCtrl
 * @description
 * # IoTManagerDashboardCtrl
 *
 * Controller for the IoT Manager Dashboard view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('IoTManagerDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {

        $scope.unregisteredNodes = [];
        $scope.menuList = [
            {href : "SmartCluster", name : "Smart Cluster Manager"},
            {href : "SmartNode", name : "Smart Node Manager"}
            ];
        $http.get("/smart_cluster/get/all").then(function (value) {
           $scope.clusterList = value.data;
        });

        $scope.clusterCreateData = {
            idSmartCluster:null,
            name:"",
            model:"",
            make:"",
            installationDate:"",
            location:{
                longitude:0,
                latitude:0,
                state:"",
                city:"",
                street:"",
                zipCode:0,
                idLocation:null
            },
            url:"http://localhost:8080"
        };

        $scope.smartNodeAddData = {
            idSmartNode:null,
            name:"",
            model:"",
            make:"",
            installationDate:"",
            location:$scope.clusterCreateData.location,
            smartCluster:$scope.clusterCreateData
        };


        $scope.submitCreateClusterForm = function(){

            $http.post("/smart_cluster/create",$scope.clusterCreateData).then(function (value) {

                $("#registerModalHeader").html(value.data);
                $("#registerModalHeader").css("background-color","#108a15");
                $("#create_cluster").hide();
            });


        };

        $scope.submitAddNodeForm = function(){

            $scope.smartNodeAddData.location = $scope.smartNodeAddData.smartCluster.location;
            var id = $scope.smartNodeAddData.smartCluster.idSmartCluster;
            $scope.smartNodeAddData.smartCluster = null;
            var holder = {
                smartNode:$scope.smartNodeAddData,
                idSmartCluster: id
            };

            $http.post("/smart_node/create",holder).then(function (value) {

                $("#addSmartModalHeader").html(value.data);
                $("#addSmartModalHeader").css("background-color","#108a15");
                $("#add_node").hide();
            });
        };


        $scope.onClickViewNode = function(cluster){
            $scope.currentCluster = cluster;
            var url = "/smart_node/nodes";
            $http.post(url, cluster).then(function (value){
                $scope.nodeList = value.data;
                getUnregisteredNodes();
            });
        };

        function getUnregisteredNodes(){
            var url = "/smart_node/unregistered?clusterId=" + $scope.currentCluster.idSmartCluster;
            $http.get(url)
                .success(function(data){
                    console.log('Getting unregistered nodes', data);
                    for(var i=0;i<data.length;i++){
                        $scope.nodeList.push(data[i]);
                    }
                })
                .error(function(error){
                    console.error(error);
                });
        }

        $scope.registerNode = function(node){
            console.log("Register node request sent node:", node);
            var url = "/smart_node/register";
            $http.post(url, node).success(function(data){
                console.log("Node registered node:", data);
            })
                .error(function(error){
                    console.error(error);
                });
        };


        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.iot-manager-dashboard-container').height(height -120);
        }
    }
]);

angular.module('navBar').controller('navBarController', ['$scope', function($scope){

}]);