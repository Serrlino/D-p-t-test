
import React, { useState } from "react";
import ActionEns from '../Components/ActionEns';

function App() {

    let [teachers, setTeachers] = useState([])
    let [teacherId, setTeacherId] = useState('')

    let [teacherName, setTeacherName] = useState('')
    let [teacherAddress, setTeacherAddress] = useState('')
    let [teacherSex, setTeacherSex] = useState('')
    let [teacherContact, setTeacherContact] = useState('')
    let [teacherOffice, setTeacherOffice] = useState()
    let [teacherFonction, setTeacherFonction] = useState('')
    let [teacherBirthDate, setTeacherBirthDate] = useState('')  

    const [isOpen, setIsOpen] = useState(false);

    const toggleModal = () => {
        setIsOpen(!isOpen);
    };

  
    let findTeacher = () =>
      {
        fetch('http://localhost:8081/teacher/index').then(response => {
          if(!response.ok){
            throw new Error("Erreur lors de la création de l'enseignant");
          }
          return response.json();
        }).then(data => {
            setTeachers(data) 
            console.log(data)
        }).catch(error =>{
          alert(error.message)          
        });
      }
      findTeacher()
    //   console.log(teachers)

      let [offices, setOffices] = useState([]);
  
      fetch('http://localhost:8081/office/read').then(response => {
          if(!response.ok){
            throw new Error("Erreur lors de la création de l'enseignant");
          }
          return response.json();
        }).then(data => {
            setOffices(data)
            // console.log(offices)
        }).catch(error =>{
                alert(error.message)
          });

      let [fonctions, setFonctions] = useState([])
  
      let findFonction = () =>
        {
          fetch('http://localhost:8081/fonction/read').then(response => {
            if(!response.ok){
              throw new Error("Erreur lors de la création de l'enseignant");
            }
            return response.json();
          }).then(data => {
            setFonctions(data) 
              // console.log(data)
          }).catch(error =>{
            
              alert(error.message)
            });
        }

        let deleteTeacher = (delRegisterNumber) =>
          {
              fetch('http://localhost:8081/teacher/delete/' + delRegisterNumber, {method : 'delete'}).then(response => {
                  if(!response.ok){
                    throw new Error("Erreur lors de la création de l'enseignant");
                  }
                  return response.json();
                }).then(data => { 
                    alert('Enseignant supprimé')
                }).catch(error =>{
                  alert(error.message)
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
              <label for="fname">Nom & Prenom</label>
            </div>
            <div className="col-75">
              <input type="text" id="_fname" name="firstname" placeholder="Nom et prenom de l'enseignant.." />
              {/* value={teacherName} onChange={(e) => {setTeacherName(e.target.value)}} */}
            </div>
          </div>


          <div className="row">
            <div className="col-25">
              <label for="lname">Sexe</label>
            </div>
            <div className="col-75">
              <input type="text" id="_lname" name="lastname" placeholder="Genre de l'enseignant.." />
              {/* value={teacherSex} onChange={(e) => {setTeacherSex(e.target.value)}} */}
            </div>
          </div>


          <div className="row">
            <div className="col-25">
              <label for="lname">Adresse </label>
            </div>
            <div className="col-75">
              <input type="text" id="_add" name="add" placeholder="Adresse de l'enseignant.." />
              {/* value={teacherAddress} onChange={(e) => {setTeacherAddress(e.target.value)}} */}
            </div>
          </div>


          <div className="row">
            <div className="col-25">
              <label for="lname">Contact</label>
            </div>
            <div className="col-75">
              <input type="number" id="_tel" name="tel" telplaceholder="Numero de tel de l'enseignant.." />
              {/* value={teacherContact} onChange={(e) => {setTeacherContact(parseInt(e.target.value))}} */}
            </div>
          </div>

          <div className="row">
            <div className="col-25">
              <label for="lname">Bureau</label>
            </div>
            <div className="col-75">
              <select name="office" id="_office" onChange={(e) => {setTeacherOffice((e.target.value))}} value={teacherOffice}>
                            {offices.map((office) => { 
                                return(
                                <option value={office.officeId}>
                                    {office.officeName}
                                </option>)
                            })}
                
              </select>
            </div>
          </div>


          <div className="row">
            <div className="col-25">
              <label for="lname">Fonction</label>
            </div>
            <div className="col-75">
              <select name="fonc" id="_fonc" onClick={findFonction} onChange={(e) => {setTeacherFonction((e.target.value))}} value={teacherFonction}>
                            {fonctions.map((fonction) => { 
                                return(
                                <option value={fonction.fonctionName}>
                                    {fonction.fonctionName}
                                </option>)
                            })}
                
              </select>

            </div>
          </div>


          <div className="row">
            <div className="col-25">
              <label for="lname">Date de Naissance</label>
            </div>
            <div className="col-75">
              <input type="Date" id="_date" name="date" placeholder="Date de Naissancce de l'enseignant.." value={teacherBirthDate} onChange={(e) => {setTeacherBirthDate(e.target.value)}}/>
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
                                            deleteTeacher(teacher.registerNumber)}} className="btn danger">Supprimer</button>

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
