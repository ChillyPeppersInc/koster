function loadMap() {
    // Инициализация карты
    ymaps.ready(init);

    function init() {
        var myMap = new ymaps.Map("map", {
            center: [55.792159, 49.122014], // двойка
            zoom: 17
        });

    var myPlacemark; // marker

    myMap.events.add('click', function (e) {
        var coords = e.get('coords');

        // Если маркер уже есть, удаляем его
        if (myPlacemark) {
            myMap.geoObjects.remove(myPlacemark);
        }

        // Создаем новый маркер
        myPlacemark = new ymaps.Placemark(coords, {
            hintContent: 'Выбранная точка',
            balloonContent: 'Координаты: ' + coords
        }, {
            preset: 'islands#redDotIcon'
        });

        // Добавляем маркер на карту
        myMap.geoObjects.add(myPlacemark);

        document.getElementById('geoposition').value = coords;
        }
    );
    }
});