
import React, { useState } from "react";
import ActionEns from "../Components/ActionEns";

function EnseignantForm() {
    const link1 = 'premier lien';
    const link2 = 'deuxieme lien';

      let[officeId, setOfficeId] = useState()


  let [teacherName, setTeacherName] = useState('')
  let [teacherAddress, setTeacherAddress] = useState('')
  let [teacherSex, setTeacherSex] = useState('')
  let [teacherContact, setTeacherContact] = useState('')
  let [teacherOffice, setTeacherOffice] = useState()
  let [teacherFonction, setTeacherFonction] = useState('')
  let [teacherBirthDate, setTeacherBirthDate] = useState('')

  let addTeacher = async (event) =>
    {
      let path = teacherName + '/' + teacherAddress + '/' + teacherBirthDate
      + '/' + teacherSex + '/' + teacherContact + '/' + 6 + '/' + teacherFonction

      event.preventDefault();
      let data = await fetch('http://localhost:8081/teacher/create/' + path, {method : 'post'}).then(response => {
        if(!response.ok){
          throw new Error('Erreur lors de la création du produit');
        }
        return response.text();
      }).then(data => {
        alert(data);
      }).catch(error =>{
        alert(error);
      });
    }

    let [offices, setOffices] = useState([])
  
    let findOffice = () =>
      {
        fetch('http://localhost:8081/office/read').then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création du produit');
          }
          return response.json();
        }).then(data => {
            setOffices(data) 
            // console.log(data)
        }).catch(error =>{
          
        });
      }

      let [fonctions, setFonctions] = useState([])
  
      let findFonction = () =>
        {
          fetch('http://localhost:8081/fonction/read').then(response => {
            if(!response.ok){
              throw new Error('Erreur lors de la création du produit');
            }
            return response.json();
          }).then(data => {
            setFonctions(data) 
              // console.log(data)
          }).catch(error =>{
            
          });
        }


      let changeOff = (event) =>
        {
          let val = event.target.value
          setTeacherOffice(val)
          let d = val.split(':')
          d = parseInt(d[0])
          setOfficeId(d[0])


          console.log(officeId)
        }


        let changeFonc = (event) =>
          {
            setTeacherFonction(event.target.value)
            console.log(teacherFonction)
          }
  
  
  return (
    <div className="content">
        <h2>Gerer l'Enseignant</h2>
        <hr/>
        <div className="row">

          <ActionEns/>

   
    <div className="column2">
        <h2>Ajouter un enseignant </h2>
        <p> Servez vous de ce formulaire pour  renseigner les enseignants de l'etablissement </p>

      <div className="container">
        <form action="#">

          <div className="row">
            <div className="col-25">
              <label for="fname">Nom & Prenom</label>
            </div>
            <div className="col-75">
              <input type="text" id="_fname" name="firstname" placeholder="Nom et prenom de l'enseignant.." value={teacherName} onChange={(e) => {setTeacherName(e.target.value)}}/>
            </div>
          </div>

          <div className="row">
            <div className="col-25">
              <label for="lname">Sexe</label>
            </div>
            <div className="col-75">
              <input type="text" id="_lname" name="lastname" placeholder="Genre de l'enseignant.." value={teacherSex} onChange={(e) => {setTeacherSex(e.target.value)}}/>
            </div>
          </div>
          <div className="row">
            <div className="col-25">
              <label for="lname">Adresse </label>
            </div>
            <div className="col-75">
              <input type="text" id="_add" name="add" placeholder="Adresse de l'enseignant.." value={teacherAddress} onChange={(e) => {setTeacherAddress(e.target.value)}}/>
            </div>
          </div>

          <div className="row">
            <div className="col-25">
              <label for="lname">Contact</label>
            </div>
            <div className="col-75">
              <input type="number" id="_tel" name="tel" telplaceholder="Numero de tel de l'enseignant.." value={teacherContact} onChange={(e) => {setTeacherContact(parseInt(e.target.value))}}/>
            </div>
          </div>

          <div className="row">
            <div className="col-25">
              <label for="lname">Bureau</label>
            </div>
            <div className="col-75">
              {/* <input type="text" id="lname" name="lastname" placeholder="Bureau de l'enseignant.." value={teacherOffice} onChange={(e) => {setTeacherOffice(e.target.value)}}/> */}

              <select name="office" id="_office" onClick={findOffice} onChange={changeOff} value={teacherOffice}>
                            {offices.map((office) => { 
                                return(
                                <option>
                                    {office.officeId}:{office.officeName}
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
              {/* <input type="text" id="lname" name="lastname" placeholder="Fonction de l'enseignant.." value={teacherFonction} onChange={(e) => {setTeacherFonction(e.target.value)}}/> */}

              <select name="fonc" id="_fonc" onClick={findFonction} onChange={changeFonc} value={teacherFonction}>
                            {fonctions.map((fonction) => { 
                                return(
                                <option >
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

          <div className="row">
            <button value="Submit" width="10" onClick={addTeacher}> Enregistrer </button>
          </div> 
        </form>
      </div>
    </div>
  </div>
</div>
  );
}

export default EnseignantForm;
