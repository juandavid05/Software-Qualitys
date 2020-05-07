<?php
include_once '../../config/loader.php';

/*
 modulo de categorias y otras
*/
use Vendor\Utility\Html;
use Vendor\Utility\Form\DropDown;
use Vendor\Utility\Query\DepAndTown;
use Module\Users\Entity\UserSession;

$objCtrProfile = new DepAndTown();
$objSession = new UserSession();

$arrayDepartment = $objCtrProfile->getAssocDepartamet();
$session = false;
/*
 esta logueado?
*/
if (isset($_SESSION['cod'])) {
    $session = true;
}
?>

<!DOCTYPE html>
<html lang="es">
<?php
Html::headIndex("Categoría y descripción - ecommerce");
?>
<body>
<?php
if (!$session) {
    header('location: ../../module/users/view/login.php');
} else {
    include_once '../home/menu.php';
    ?>
    <main class="main">
        <div class="container-fluid">
            <div class="publish-wrapper mt-5">
                <div class="row">
                    <section class="publish-data">
                        <div class="box">
                            <ul>
                                <li class="active">
                                    <h6>Categoría y descripción</h6>
                                </li>
                                <li>
                                    <h6>Precio y cantidad</h6>
                                </li>
                                <li>
                                    <h6>Agregar fotos</h6>
                                </li>
                                <li>
                                    <h6>Publicar</h6>
                                </li>
                            </ul>
                            <section class="category-description p-s-3 px-m-5 pt-m-4">
                                <h6 class="mb-4">Seleccione la categoría</h6>
                                <div class="category-wrapper">
                                    <div id="c1" class="category-item">
                                        <ul id="parent">
                                            <li id="1" onClick="clicked(this.id)">Animales de granja
                                            </li>
                                            <li id="2" onClick="clicked(this.id)">Verduras</li>
                                            <li id="3" onClick="clicked(this.id)">Frutas</li>
                                            <li id="4" onClick="clicked(this.id)">No consumo</li>
                                            <li id="5" onClick="clicked(this.id)">Cereales</li>
                                            <li id="6" onClick="clicked(this.id)">Procesados</li>
                                            <li id="7" onClick="clicked(this.id)">Animales de granja
                                            </li>
                                            <li id="8" onClick="clicked(this.id)">Verduras</li>
                                            <li id="9" onClick="clicked(this.id)">Frutas</li>
                                        </ul>
                                    </div>
                                    <div id="c2" class="category-item hide">
                                        <ul id="parent">
                                            <li id="101" onClick="clicked(this.id)">Animales de
                                                granja
                                            </li>
                                            <li id="102" onClick="clicked(this.id)">Verduras</li>
                                            <li id="103" onClick="clicked(this.id)">Frutas</li>
                                            <li id="104" onClick="clicked(this.id)">No consumo</li>
                                            <li id="105" onClick="clicked(this.id)">Cereales</li>
                                            <li id="106" onClick="clicked(this.id)">Procesados</li>
                                            <li id="107" onClick="clicked(this.id)">Animales de
                                                granja
                                            </li>
                                            <li id="108" onClick="clicked(this.id)">Verduras</li>
                                            <li id="109" onClick="clicked(this.id)">Frutas</li>
                                            <li id="110" onClick="clicked(this.id)">No consumo</li>
                                            <li id="111" onClick="clicked(this.id)">Cereales</li>
                                            <li id="112" onClick="clicked(this.id)">Procesados</li>
                                        </ul>
                                    </div>
                                    <div id="c3" class="category-item hide">
                                        <ul id="parent">
                                            <li id="1000" onClick="clicked(this.id)">Animales de
                                                granja
                                            </li>
                                            <li id="1001" onClick="clicked(this.id)">Verduras</li>
                                            <li id="1002" onClick="clicked(this.id)">Frutas</li>
                                            <li id="1003" onClick="clicked(this.id)">No consumo</li>
                                            <li id="1004" onClick="clicked(this.id)">Cereales</li>
                                            <li id="1005" onClick="clicked(this.id)">Procesados</li>
                                            <li id="1006" onClick="clicked(this.id)">Animales de
                                                granja
                                            </li>
                                            <li id="1007" onClick="clicked(this.id)">Verduras</li>
                                            <li id="1008" onClick="clicked(this.id)">Frutas</li>
                                            <li id="1009" onClick="clicked(this.id)">No consumo</li>
                                            <li id="1010" onClick="clicked(this.id)">Cereales</li>
                                            <li id="1011" onClick="clicked(this.id)">Procesados</li>
                                        </ul>
                                    </div>
                                    <div id="c4" class="category-item hide">
                                        <ul id="parent">
                                            <li id="10000" onClick="clicked(this.id)">Animales de
                                                granja
                                            </li>
                                            <li id="10001" onClick="clicked(this.id)">Verduras</li>
                                            <li id="10002" onClick="clicked(this.id)">Frutas</li>
                                            <li id="10003" onClick="clicked(this.id)">No consumo</li>
                                            <li id="10004" onClick="clicked(this.id)">Cereales</li>
                                            <li id="10005" onClick="clicked(this.id)">Procesados</li>
                                            <li id="10006" onClick="clicked(this.id)">Animales de
                                                granja
                                            </li>
                                            <li id="10007" onClick="clicked(this.id)">Verduras</li>
                                            <li id="10008" onClick="clicked(this.id)">Frutas</li>
                                            <li id="10009" onClick="clicked(this.id)">No consumo</li>
                                            <li id="10010" onClick="clicked(this.id)">Cereales</li>
                                            <li id="10011" onClick="clicked(this.id)">Procesados</li>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                            <div class="publish-item">
                                <form action="controller/categoryanddescriptionpostctrl.php" id="form-basic-data"
                                      method="post">
                                    <div class="p-title">
                                        <div class="input-field p-label">
                                            <label for="title">Título</label>
                                        </div>
                                        <div class="p-input">
                                            <div class="input-field">
                                                <input type="text" placeholder="Titulo de tu producto" id="title"
                                                       name="title">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="p-description">
                                        <div class="input-field p-label">
                                            <label for="description">Descripción</label>
                                        </div>
                                        <div class="p-input">
                                            <div class="input-field">
                                                <textarea name="description" id="description"
                                                          placeholder="Escribe tus descripción"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="p-location">
                                        <div class="input-field p-label">
                                            <label for="location">Ubicación</label>
                                        </div>
                                        <div class="p-input p-select">
                                            <div class="p-select-wrapper-48">
                                                <div class="input-field">
                                                    <?php
                                                    echo DropDown::show("department", $arrayDepartment, "select-custom", "Seleccione su departamento", "", "");
                                                    ?>
                                                </div>
                                            </div>
                                            <div class="p-select-wrapper-48">
                                                <div class="input-field">
                                                    <select id="town" name="town" class="select-custom">
                                                        <option value="" disabled selected>Seleccione su municipio
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="p-next btn-vc-link waves-effect" name="btn-basic-data"
                                            id="btn-one">
                                        Siguiente
                                    </button>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </main>
    <?php
    Html::importFooterIndex();
    Html::importIndexJs();
    /*
 todos los footers de la pgina
*/
}
?>
<script src="../../public/js/ecommerce/publish.js"></script>
<script src="../../public/js/ecommerce/load/loadtownresults.js"></script>
</body>
</html>