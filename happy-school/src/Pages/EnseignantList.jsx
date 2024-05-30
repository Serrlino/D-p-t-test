
import React, { useState } from "react";
import ActionEns from '../Components/ActionEns';

function App() {

    let [teachers, setTeachers] = useState([])
    let [teacherId, setTeacherId] = useState('')

    const [isOpen, setIsOpen] = useState(false);

    const toggleModal = () => {
        setIsOpen(!isOpen);
    };

  
    let findTeacher = () =>
      {
        fetch('http://localhost:8081/teacher/index').then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création du produit');
          }
          return response.json();
        }).then(data => {
            setTeachers(data) 
            console.log(data)
        }).catch(error =>{
          
        });
      }
      findTeacher()
    //   console.log(teachers)

    let deleteTeacher = () =>
      {
          fetch('http://localhost:8081/teacher/delete/' + teacherId, {method : 'delete'}).then(response => {
              if(!response.ok){
                throw new Error('Erreur lors de la création du produit');
              }
              return response.json();
            }).then(data => { 
              //   alert('Enseignant supprimé')
                alert(teacherId)
            }).catch(error =>{
          });
    
      }

  return (
    <div className="content">
        <h2>Gerer les Enseignants</h2>
        <hr/>
        <div className="row">

            <ActionEns/>
            {/* Corp du modal pour la modification */}
            <div>
                
                {isOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content"> 
                        <div className="modal-close" onClick={toggleModal}> &times; </div>
                            <h2>Modification</h2> 
                            <p>Modifier les informations du Bureau.</p>
                            <div className="row">
                                <div className="col-25">
                                <label for="fname">Nom </label>
                                </div>
                                <div className="col-75">
                                <input type="text" id="fname" name="firstname" placeholder="Libelle du bureau .."/>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-25">
                                <label for="subject">Description</label>
                                </div>
                                <div className="col-75">
                                <textarea id="subject" name="subject" placeholder="Description du bureau .."  style={{ height:200 + 'px' }}></textarea>
                                </div>
                            </div>
                            
                            <div className="modal-footer">
                                <button onClick={toggleModal}>Valider</button>
                            </div>
                        </div>
            </div>
            // fin du corp du modal
            
      )}
    </div>
            <div className="column2">
                <h2>Liste des  Enseignants </h2>
                <p> Le tableau suivant contient les enseignants enregistres dans l'etablissement</p>

                <div className="container">
                    <table>
                      <tr>
                            <th>Nom & prenom </th>
                            <th>Adresse</th>
                            <th>Date De Naissance</th>
                            <th>Sexe</th>
                            <th>Contact</th>
                            <th>Bureau</th>
                            <th>Fonction</th>
                            <th>Actions</th>
                        </tr>


                            {teachers.map((teacher) => { 
                                return(
                                <tr>
                                    <td>{teacher.teacherName}</td>
                                    <td>{teacher.teacherAddress}</td>
                                    <td>{teacher.teacherBirthDate}</td>
                                    <td>{teacher.teacherSex}</td>
                                    <td>{teacher.teacherContact}</td>
                                    <td>{teacher.teacherOffice.officeName}</td>
                                    <td>{teacher.teacherFonction.fonctionName}</td>
                                    <td >

                                        <button onClick={toggleModal} className="btn default">mettre a jour</button>
                                <button onClick={() => {
                                            setTeacherId(teacher.teacherId); deleteTeacher()}} className="btn danger">Supprimer</button>

                                    </td>
                                </tr>)
                            })}
                    </table>
                </div>
            </div>
        </div>
    </div>
  );
}

export default App;
