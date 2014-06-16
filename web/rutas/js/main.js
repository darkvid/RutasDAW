            var puntos;
            $(document).ready(function() {
                $.ajax({
                    url: "http://localhost:8080/rutas/rest/kml/14"
                }).then(function(data) {
                    puntos = data;
                    console.log(data.length);
                });
            });
        
          function initialize() {
              
            var cazorla = new google.maps.LatLng(puntos[0].latitud, puntos[0].longitud);
            var mapOptions = {
                center:cazorla, 
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.TERRAIN
            };

            var map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);

            //añado marcador del principio y final
            var inicio = new google.maps.LatLng(puntos[0].latitud, puntos[0].longitud);
            var final = new google.maps.LatLng(puntos[puntos.length-1].latitud, puntos[puntos.length-1].longitud);
            var marker = new google.maps.Marker({
                position: inicio,
                map: map,
                title: 'Inicio'
            });
            var marker = new google.maps.Marker({
                position: final,
                map: map,
                title: 'Final'
            });

            var flightPlanCoordinates = new Array();
            for(var i=0;i<puntos.length;i++){
                flightPlanCoordinates.push(new google.maps.LatLng(puntos[i].latitud,puntos[i].longitud));
            }
                
            var flightPath = new google.maps.Polyline({
              path: flightPlanCoordinates,
              geodesic: true,
              strokeColor: '#FF0000',
              strokeOpacity: 1.0,
              strokeWeight: 2
            });
            console.log("uo");
            flightPath.setMap(map);
          }
        
          //google.maps.event.addDomListener(window, 'load', initialize);
          setTimeout(initialize,1500);