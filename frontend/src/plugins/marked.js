import { marked } from 'marked';

// 커스텀 렌더러 생성
const renderer = new marked.Renderer();

renderer.listitem = function(text) {
    if(text.startsWith('* ')) {
        text = text.slice(2);
    }

    return `<li>${text}</li>`;
};

renderer.list = function(body, ordered) {
    const type = ordered ? 'ol' : 'ul';
    return `<${type}>${body}</${type}>`;
  };

marked.setOptions({
    renderer: renderer,
    gfm: true,
    breaks: true,
    sanitize: false,
    highlight: function(code, lang) {
      const hljs = require('highlight.js');
      const language = hljs.getLanguage(lang) ? lang : 'plaintext';
      return hljs.highlight(code, { language }).value;
    }
})


export default marked;