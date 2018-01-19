/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 2
        CREATED BY: SHWETA DIXIT - 2017

        TITLE --- VERSION 2 - SLIDER TO SEPARATE BOX IN TWO LAYERS

        (Measurements in mm)

*/

// Modelling the slider to separate the box in two layers
module slider_layer()
{

    difference()
    {
        cube([140, 148, 5]);

        // Adding the hole to let wires through
        translate([55, 116, 0])
        {
            cube([30, 32, 5]);
        }
    }

    // Adding the handle
    translate([55, -20, 1])
    {
        cube([30, 20, 3]);
    }
}
slider_layer();
