'use strict';

/**
 *
 * @name UserDashboardCtrl
 * @description
 * # UserDashboardCtrl
 *
 * Controller for the User Dashboard view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('UserDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        /***** Variables *****/
        $scope.streetStat = [];
        $scope.streetMapViewStat = [];
        $scope.zip = '';
        $scope.state = '';
        $scope.city = '';
        $scope.viewSelector = 'Statistics';
        $scope.map = null;

        /***** Startup actions *****/
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        showTableStatView();
        initGoogleMap();

        /***** Callbacks *****/
        $scope.search = function () {
            var url = '/infrastructure/stat/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.streetStat = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };
        $scope.viewChanged = function () {
            console.log("The view was changed to " + $scope.viewSelector);
            switch ($scope.viewSelector) {
                case 'Statistics':
                    showTableStatView();
                    break;
                case 'Map View':
                    showMapStatView();
                    break;
                case 'Connectivity View':
                    showConnectivityStatView();
                    break;
                case 'Sensor Data View':
                    showSensorDataView();
                    break;
            }

        };

        /***** Internal functions *****/
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.user-dashboard-container').height(height -120);
        }

        function showTableStatView(){
            $('.table-data-view').removeClass('disabled');

            $('.map-data-view').addClass('disabled');
        }

        function showMapStatView(){
            $('.map-data-view').removeClass('disabled');

            $('.table-data-view').addClass('disabled');

            if($scope.streetMapViewStat.length === 0){
                getMapViewData();
            } else {
                addClustersToMap($scope.map, $scope.streetMapViewStat);
            }
        }

        function showConnectivityStatView(){

        }

        function showSensorDataView(){

        }

        function getMapViewData(){
            var url = '/infrastructure/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting data from server for map view', data);
                    $scope.streetMapViewStat = data;
                    addClustersToMap($scope.map, $scope.streetMapViewStat);
                })
                .error(function(error){
                    console.error(error);
                });
        }


        function addClustersToMap(map, clusters){
            console.log("Add clusters into map");
            for(var i=0;i<clusters.length;i++){
                addOverlaytoMap(clusters[i].location.longitude, clusters[i].location.latitude, 'image/cluster-icon.png', map);
                for(var j=0;j<clusters[i].smartNodeSet.length;j++){
                    var node = clusters[i].smartNodeSet[j];
                    addOverlaytoMap(node.location.longitude, node.location.latitude, 'image/node-icon.png', map);
                    for (var t=0;t<node.sensorSet.length;t++){
                        var sensor = node.sensorSet[t];
                        addOverlaytoMap(sensor.location.longitude, sensor.location.latitude, 'image/sensor-icon.png', map);
                    }
                }
            }


        }

        function addOverlaytoMap(lat, lng, img, map){
            var bounds = new google.maps.LatLngBounds(
                new google.maps.LatLng(lat, lng),
                new google.maps.LatLng(lat + 0.0004, lng + 0.0004));
            var overlay = new USGSOverlay(bounds, img, map);
        }







        /********************* INIT Google Map *******************/

        function initGoogleMap(){
            $scope.map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 37.335694, lng: -121.902710},
                zoom: 16
            });
        }

        USGSOverlay.prototype = new google.maps.OverlayView();

        /** @constructor */
        function USGSOverlay(bounds, image, map) {

            // Initialize all properties.
            this.bounds_ = bounds;
            this.image_ = image;
            this.map_ = map;

            // Define a property to hold the image's div. We'll
            // actually create this div upon receipt of the onAdd()
            // method so we'll leave it null for now.
            this.div_ = null;

            // Explicitly call setMap on this overlay.
            this.setMap(map);
        }
        /**
         * onAdd is called when the map's panes are ready and the overlay has been
         * added to the map.
         */
        USGSOverlay.prototype.onAdd = function() {

            var div = document.createElement('div');
            div.style.borderStyle = 'none';
            div.style.borderWidth = '0px';
            div.style.position = 'absolute';

            // Create the img element and attach it to the div.
            var img = document.createElement('img');
            img.src = this.image_;
            img.style.width = '100%';
            img.style.height = '100%';
            img.style.position = 'absolute';
            div.appendChild(img);

            this.div_ = div;

            // Add the element to the "overlayLayer" pane.
            var panes = this.getPanes();
            panes.overlayLayer.appendChild(div);
        };

        USGSOverlay.prototype.draw = function() {

            // We use the south-west and north-east
            // coordinates of the overlay to peg it to the correct position and size.
            // To do this, we need to retrieve the projection from the overlay.
            var overlayProjection = this.getProjection();

            // Retrieve the south-west and north-east coordinates of this overlay
            // in LatLngs and convert them to pixel coordinates.
            // We'll use these coordinates to resize the div.
            var sw = overlayProjection.fromLatLngToDivPixel(this.bounds_.getSouthWest());
            var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_.getNorthEast());

            // Resize the image's div to fit the indicated dimensions.
            var div = this.div_;
            div.style.left = sw.x + 'px';
            div.style.top = ne.y + 'px';
            div.style.width = (ne.x - sw.x) + 'px';
            div.style.height = (sw.y - ne.y) + 'px';
        };

        USGSOverlay.prototype.onRemove = function() {
            this.div_.parentNode.removeChild(this.div_);
            this.div_ = null;
        };
    }
]);