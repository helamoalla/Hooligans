

function createNotification(title, content) {
    if (Notification.permission === 'granted') {
      var notification = new Notification(title, {
        body: content
      });
    }
    else if (Notification.permission !== 'denied') {
      Notification.requestPermission(function(permission) {
        if (permission === 'granted') {
          var notification = new Notification(title, {
            body: content
          });
        }
      });
    }
  }


  function updatePanierIcon() {
    // Récupérer le nombre de produits dans le panier
    $.ajax({
      url: '/nbprod', // la route qui retourne le nombre de produits dans le panier
      type: 'GET',
      dataType: 'json',
      success: function(response) {
        // Mettre à jour le contenu de l'icône du panier
        var icone = document.querySelector('#cart-icon');
        //cartIcon.textContent = response.nbp;
        icone.innerHTML += " (" + response.nbp + ")";      
      }
    });
  }

  function init() {
    updatePanierIcon();
  }
  
  window.onload = init;


