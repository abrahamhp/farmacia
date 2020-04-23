<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Farmacia</title>
</head>

<script>
// On mouse-over, execute myFunction
function mostrarMapa() {
   //mostrar mapa
}
</script>
	<body>

<form>
  <label for="fname">Comuna:</label><br>
  <input type="text" id="fname" name="fname"><br>
  <label for="lname">Nombre local:</label><br>
  <input type="text" id="lname" name="lname">

<input type="submit" value="Buscar"  onclick="mostrarMapa()">



		<div id ="map"> 
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC_kX7Pa7K2vIztqsVywPMVuQBA3zKKsDc&callback=initMap" async defer></script>
	<script>
		
      var map;
  	 function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
		  center: {lat: -33.733185, lng: -70.737136},
          zoom: 13,
        });
        var marker = new google.maps.Marker({
          position: {lat: -33.733185, lng: -70.737136},
          map: map,
	  title: 'Acuario de Gijón'
        });
      }
 
      
	</script>
	</div> 
</form>

	</body> 

</html>