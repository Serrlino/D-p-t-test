import React from "react";
import '../main.css';

function Index(){
    return (
        <div className="body">
            <div className="timeline">
                <div className="container1 left">
                    <div className="content1">
                        <h2>Section</h2>
                        <p>L'etablissement est subdiviser en section. 
                            qui seront les plus grandes division de l'etablissement. 
                        </p>
                        <a href="sectionList"> consulter les sections disponible  </a>
                    </div>
                </div>
                <div className="container1 right">
                    <div className="content1">
                        <h2>Cycle</h2>
                        <p>Les sections sont ensuite subdiviser en cycle, ceci dans le but de rendre le cursus
                            de l'apprenant incrementale et evolutif dans le temps.
                        </p>
                        <a href="/cycleList"> consulter les cycles disponible  </a>
                    </div>
                </div>
                <div className="container1 left">
                    <div className="content1">
                        <h2>Serie</h2>
                        <p>Chaque cycle est compose de serie </p>
                        <a href="/serieList"> consulter les series disponible  </a>
                    </div>
                    
                </div>
            

                <div className="container1 right">
                    <div className="content1">
                        <h2>Classe / Grade</h2>
                        <p>Chaque cycle contient un nombre de classe qui lui sont propre</p>
                        <a href="/gradeList"> consulter les classes disponible  </a>
                    </div>
                    
                </div>
                <div className="container1 left">
                    <div className="content1">
                        <h2>Matiere</h2>
                        <p>Chaque classe contient des matieres qui lui sont propre</p>
                        <a href="/matiereList"> consulter les matieres disponible  </a>
                    </div>
                </div>
                
                <div className="container1 right">
                    <div className="content1">
                        <h2>Enseignants</h2>
                        <p>Chaque matiere est donne par un Enseignant</p>
                        <a href="/ensList"> consulter les enseignants de l'etablissement </a>
                    </div>
                </div>
                <div className="container1 left">
                    <div className="content1">
                        <h2>Fonctions</h2>
                        <p>Chaque Enseignant occupe une fonction specifique dans l'etablissement</p>
                        <a href="/fonctionList"> consulter les fonctions  </a>
                    </div>
                </div>
                <div className="container1 right">
                    <div className="content1">
                        <h2>Bureaux</h2>
                        <p>les Bureaux sont octroyes aux enseignants a raison des fonctions qu'ils occupent
                            au sein de l'etablissement.</p>
                        <a href="/bureauList"> consulter les Bureaux. </a>
                    </div>
                </div>
            </div>
        </div>    
    );
}

export default Index;