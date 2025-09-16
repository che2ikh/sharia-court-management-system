  function replaceInputs() {
      const walker = document.createTreeWalker(document.body, NodeFilter.SHOW_TEXT, null);
      const nodes = [];
        while (walker.nextNode()) {
  if (walker.currentNode.nodeValue.includes('...')) nodes.push(walker.currentNode);
   }
      nodes.forEach(textNode => {
  const parent = textNode.parentNode;
  const parts = textNode.nodeValue.split('...');
         for (let i = 0; i < parts.length; i++) {
          if (parts[i].length) parent.insertBefore(document.createTextNode(parts[i]), textNode);
          if (i < parts.length - 1) {
      const input = document.createElement('input');
         input.className = 'myInput';
     parent.insertBefore(input, textNode);
                          }
               }
    parent.removeChild(textNode);
                 });
      alert("replace input");
           }


                        // Function to collect input values
  function getInputValues() {
     const inputs = document.querySelectorAll('.myInput');
      let values = [];
         inputs.forEach(input => values.push(input.value));
          alert("get input values ");

        return values;
     }

  function submitValues() {
 const values = getInputValues();
 alert("javaApp: "+window.javaApp);
 alert("typeof window.javaApp.handleInputs: "+window.javaApp.handleInputs)
 setTimeout(() => {
   if (window.javaApp && typeof window.javaApp.handleInputs === 'function') {
         alert(values);
   window.javaApp.handleInputs(values);
     } else {
        alert("warning!!!!!!!");
            }
        }, 100); // wait 100ms
      }
