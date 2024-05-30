


function Navbar() {
  return (
    <div class="topnav" id="myTopnav"> 
        <h1 className="title"> <a href="/">  HappySchool </a></h1>
        <div class="link">
            <a href="/ens">Enseignant</a>
            <a href="/grade">Grade</a>
            <a href="/section">Section</a>
            <a href="/matiere">Matiere</a>
            <a href="/serie">Serie</a>
            <a href="/cycle">Cycle</a>
            <a href="/fonction">Fonction</a>
            <a href="/bureau">Bureau</a>
        </div>
    </div>
  );
}

export default Navbar;
